/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import java.util.Collections;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;


/**
 * The HumanResourceClient class is the client program for this
 * J2EE application. It publishes a message describing a new
 * hire business event that other departments can act upon. It
 * also listens for a message reporting the completion of the
 * other departments' actions and displays the results.
 */
public class HumanResourceClient {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Topic")
    private static Topic pubTopic;
    static final Object waitUntilDone = new Object();
    static SortedSet<Integer> outstandingRequests = Collections
        .synchronizedSortedSet(new TreeSet<Integer>());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        Session session;
        MessageProducer producer;
        MapMessage message;
        Queue replyQueue;
        MessageConsumer consumer;

        /*
         * Create connection.
         * Create session from connection; false means session
         *   is not transacted.
         * Create temporary queue and consumer, set message
         *   listener, and start connection.
         * Create producer and MapMessage.
         * Publish new hire business events.
         * Wait for all messages to be processed.
         * Finally, close connection.
         */
        try {
            Random rand = new Random();
            int nextHireID = rand.nextInt(100);
            int[] order;

            String[] positions = {
                    "programista", "starszy programista", "kierownik", "dyrektor"
                };
            String[] names = {
                "Stefan Rogala", "Dawid Rado³a", "Oskar Borkowski", "Stanis³aw Serowski",
                "Diana Król", "Amanda Weso³kowska", "Agata Radzi³ow", "Damian Jagie³³o",
                "Helena Robótka", "Adrianna Klepka", "G³owos³aw Jacyków", "Marek Jankowski",
                "Henryk Budka", "Marian Tudorka", "Gertruda Z¹bek",
                "Jan Nowak", "Apolonia W¹s", "Tomasz Kowalski",
                "Maria Budny", "Beata Bourbon", "Karol Jasny", "Robert Mokry"
                };

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            replyQueue = session.createTemporaryQueue();
            consumer = session.createConsumer(replyQueue);
            consumer.setMessageListener(new HRListener());
            connection.start();

            producer = session.createProducer(pubTopic);

            message = session.createMapMessage();
            message.setJMSReplyTo(replyQueue);
            order = getorder();

            for (int i = 0; i < 5; i++) {
                int currentHireID = nextHireID++;
                message.setString(
                    "HireID",
                    String.valueOf(currentHireID));
                message.setString("Name", names[order[i]]);
                message.setString(
                        "Position",
                        positions[rand.nextInt(positions.length)]);
                System.out.println(
                        "PRODUCENT: Ustawiam identyfikator na "
                        + message.getString("HireID") + ", imiê i nazwisko to "
                        + message.getString("Name") + ", a stanowisko to "
                        + message.getString("Position"));
                producer.send(message);
                outstandingRequests.add(new Integer(currentHireID));
            }

            System.out.println(
                    "Czekam na " + outstandingRequests.size() + " komunikatów");

            synchronized (waitUntilDone) {
                waitUntilDone.wait();
            }
        } catch (Exception e) {
            System.err.println(
                    "HumanResourceClient: Wyj¹tek: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println(
                            "HumanResourceClient: " + "Wyj¹tek zamkniêcia: "
                            + e.toString());
                }
            }

            System.exit(0);
        }
    }

    /**
     * Rather than risk names being repeated, generate an array with
     * all possible name positions in a random order.
     *
     * @return order    array containing unique random values
     */
    public static int[] getorder() {
        int[] order;

        order = new int[24];

        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }

        Random rgen = new Random();

        for (int i = 0; i < order.length; i++) {
            int randomPosition = rgen.nextInt(order.length);
            int temp = order[i];
            order[i] = order[randomPosition];
            order[randomPosition] = temp;
        }

        return order;
    }

    /**
     * The HRListener class implements the MessageListener
     * interface by defining an onMessage method.
     */
    static class HRListener implements MessageListener {
        /**
         * Displays the contents of a
         * MapMessage describing the results of processing the
         * new employee, then removes the employee ID from the
         * list of outstanding requests.
         *
         * @param message    the incoming message
         */
        @Override
        public void onMessage(Message message) {
            MapMessage msg = (MapMessage) message;

            try {
                System.out.println("Przetworzono zadarzenie nowego pracownika:");

                Integer id = Integer.valueOf(msg.getString("employeeId"));
                System.out.println("  Identyfikator: " + id);
                System.out.println("  Imiê i nazwisko: " + msg.getString("employeeName"));
                System.out.println(
                        "  Sprzêt: " + msg.getString("equipmentList"));
                System.out.println(
                        "  Numer biura: " + msg.getString("officeNumber"));
                outstandingRequests.remove(id);
            } catch (JMSException je) {
                System.out.println(
                        "HRListener.onMessage(): Wyj¹tek: " + je.toString());
            }

            if (outstandingRequests.size() == 0) {
                synchronized (waitUntilDone) {
                    waitUntilDone.notify();
                }
            } else {
                System.out.println(
                        "Czekam na " + outstandingRequests.size()
                        + " komunikatów");
            }
        }
    }
}
