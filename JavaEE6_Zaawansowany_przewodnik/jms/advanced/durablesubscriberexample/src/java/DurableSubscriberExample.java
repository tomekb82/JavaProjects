/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TopicSubscriber;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;


public class DurableSubscriberExample {
    static int startindex = 0;
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/DurableConnectionFactory")
    private static ConnectionFactory durableConnectionFactory;
    @Resource(lookup = "jms/Topic")
    private static Topic topic;
    String conFacName1 = "jms/ConnectionFactory";
    String conFacName2 = "jms/DurableConnectionFactory";
    String topicName = "jms/Topic";

    /**
     * Instantiates the subscriber and publisher classes.
     * Starts the subscriber; the publisher publishes some
     *   messages.
     * Closes the subscriber; while it is closed, the publisher
     *   publishes some more messages.
     * Restarts the subscriber and fetches the messages.
     * Finally, closes the connections.
     */
    public void run_program() {
        DurableSubscriber durableSubscriber = new DurableSubscriber();
        MultiplePublisher multiplePublisher = new MultiplePublisher();

        durableSubscriber.startSubscriber();
        multiplePublisher.publishMessages();
        durableSubscriber.closeSubscriber();
        multiplePublisher.publishMessages();
        durableSubscriber.startSubscriber();
        durableSubscriber.closeSubscriber();
        multiplePublisher.finish();
        durableSubscriber.finish();
    }

    /**
     * Calls the run_program method.
     *
     */
    public static void main(String[] args) {
        DurableSubscriberExample dse = new DurableSubscriberExample();

        if (args.length != 0) {
            System.out.println("Program nie przyjmuje �adnych argument�w.");
            System.exit(1);
        }

        System.out.println(
                "Fabryka po��cze� bez identyfikatora klienta: " + dse.conFacName1);
        System.out.println(
                "Fabryka po��cze� z identyfikatorem klienta: " + dse.conFacName2);
        System.out.println("Nazwa tematu: " + dse.topicName);

        dse.run_program();
        System.exit(0);
    }

    /**
     * The DurableSubscriber class contains a constructor, a
     * startSubscriber method, a closeSubscriber method, and a
     * finish method.
     *
     * The class fetches messages asynchronously, using a message
     * listener, TextListener.
     */
    public class DurableSubscriber {
        Connection connection = null;
        Session session = null;
        TextListener listener = null;
        TopicSubscriber subscriber = null;

        /**
         * Constructor: Uses the durable connection factory to
         * create a connection and session.
         */
        public DurableSubscriber() {
            /*
             * Create connection and session.
             */
            try {
                connection = durableConnectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.AUTO_ACKNOWLEDGE);
            } catch (Exception e) {
                System.err.println("Problem z po��czeniem: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }
        }

        /**
         * Stops connection, then creates durable subscriber,
         * registers message listener (TextListener), and starts
         * message delivery; listener displays the messages
         * obtained.
         */
        public void startSubscriber() {
            try {
                System.out.println("Uruchamianie subskrybenta");
                connection.stop();
                subscriber = session.createDurableSubscriber(
                            topic,
                            "MakeItLast");
                listener = new TextListener();
                subscriber.setMessageListener(listener);
                connection.start();
            } catch (JMSException e) {
                System.err.println(
                        "startSubscriber: Wyst�pi� wyj�tek: " + e.toString());
            }
        }

        /**
         * Blocks until publisher issues a control message
         * indicating end of publish stream, then closes
         * subscriber.
         */
        public void closeSubscriber() {
            try {
                listener.monitor.waitTillDone();
                System.out.println("Zamykanie subskrybenta");
                subscriber.close();
            } catch (JMSException e) {
                System.err.println(
                        "closeSubscriber: Wyst�pi� wyj�tek: " + e.toString());
            }
        }

        /**
         * Closes the connection.
         */
        public void finish() {
            if (connection != null) {
                try {
                    System.out.println(
                            "Usuni�cie przypisania do " + "trwa�ej subskrypcji");
                    session.unsubscribe("MakeItLast");
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }

        /**
         * The TextListener class implements the MessageListener
         * interface by defining an onMessage method for the
         * DurableSubscriber class.
         */
        private class TextListener implements MessageListener {
            final SampleUtilities.DoneLatch monitor = new SampleUtilities.DoneLatch();

            /**
             * Casts the message to a TextMessage and displays
             * its text. A non-text message is interpreted as the
             * end of the message stream, and the message
             * listener sets its monitor state to all done
             * processing messages.
             *
             * @param message    the incoming message
             */
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;

                    try {
                        System.out.println(
                                "SUBSKRYBENT: " + "Odczyt komunikatu: "
                                + msg.getText());
                    } catch (JMSException e) {
                        System.err.println(
                                "Wyj�tek w " + "onMessage(): "
                                + e.toString());
                    }
                } else {
                    monitor.allDone();
                }
            }
        }
    }

    /**
     * The MultiplePublisher class publishes several messages to
     * a topic. It contains a constructor, a publishMessages
     * method, and a finish method.
     */
    public class MultiplePublisher {
        Connection connection = null;
        MessageProducer producer = null;
        Session session = null;

        /**
         * Constructor: Uses the regular connection factory and the topic
         * to create a connection, session, and publisher.
         */
        public MultiplePublisher() {
            /*
             * Create connection, session, and publisher.
             */
            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.AUTO_ACKNOWLEDGE);
                producer = session.createProducer(topic);
            } catch (Exception e) {
                System.err.println("Problem z po��czeniem: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }
        }

        /**
         * Creates text message.
         * Sends some messages, varying text slightly.
         * Messages must be persistent.
         */
        public void publishMessages() {
            TextMessage message = null;
            int i;
            final int NUMMSGS = 3;
            final String MSG_TEXT = "Oto komunikat";

            try {
                message = session.createTextMessage();

                for (i = startindex; i < (startindex + NUMMSGS); i++) {
                    message.setText(MSG_TEXT + " " + (i + 1));
                    System.out.println(
                            "PRODUCENT: Publikacja " + "komunikatu: "
                            + message.getText());
                    producer.send(message);
                }

                /*
                 * Send a non-text control message indicating end
                 * of messages.
                 */
                producer.send(session.createMessage());
                startindex = i;
            } catch (JMSException e) {
                System.err.println(
                        "publishMessages: Wystapi� wyj�tek: " + e.toString());
            }
        }

        /**
         * Closes the connection.
         */
        public void finish() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}
