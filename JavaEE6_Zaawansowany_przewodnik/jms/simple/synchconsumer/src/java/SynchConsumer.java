/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;


/**
 * Klasa SynchConsumer sk³ada siê jedynie z metody main,
 * która pobiera jeden lub wiêcej komunikatów z kolejki lub tematu
 * w sposób synchroniczny.  Uruchom program w po³¹czeniu z Producer.
 * Wska¿ "kolejka" lub "temat" w wierszu poleceñ
 * w momencie uruchomienia programu.
 */
public class SynchConsumer {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Queue")
    private static Queue queue;
    @Resource(lookup = "jms/Topic")
    private static Topic topic;

    /**
     * Metoda main.
     *
     * @param args     nazwa celu u¿ywana przez przyk³ad
     */
    public static void main(String[] args) {
        String destType = null;
        Connection connection = null;
        Session session = null;
        Destination dest = null;
        MessageConsumer consumer = null;
        TextMessage message = null;

        if (args.length != 1) {
            System.err.println("Program przyjmuje jeden argument: <typ-celu>");
            System.exit(1);
        }

        destType = args[0];
        System.out.println("Typ celu to " + destType);

        if (!(destType.equals("kolejka") || destType.equals("temat"))) {
            System.err.println("Argument musi byæ typu \"kolejka\" lub \"temat\"");
            System.exit(1);
        }

        try {
            if (destType.equals("kolejka")) {
                dest = (Destination) queue;
            } else {
                dest = (Destination) topic;
            }
        } catch (Exception e) {
            System.err.println("B³¹d ustawiania celu: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * Tworzy po³¹czenie.
         * Tworzy sesjê z po³¹czenia; false oznacza, ¿e sesja
         * nie u¿ywa transakcji.
         * Tworzy konsumenta i rozpoczyna pobieranie.
         * Pobiera wszystkie komunikaty z celu a¿ do
         * otrzymania komunikatu o zakoñczeniu komunikatów.
         * Na koñcu zamyka po³¹czenie.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(dest);
            connection.start();

            while (true) {
                Message m = consumer.receive(1);

                if (m != null) {
                    if (m instanceof TextMessage) {
                        message = (TextMessage) m;
                        System.out.println(
                                "Otrzymano komunikat: " + message.getText());
                    } else {
                        break;
                    }
                }
            }
        } catch (JMSException e) {
            System.err.println("Wystapi³ wyj¹tek: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}
