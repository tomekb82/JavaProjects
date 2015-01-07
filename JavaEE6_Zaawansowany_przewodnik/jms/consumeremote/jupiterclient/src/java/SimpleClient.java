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
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.JMSException;
import javax.annotation.Resource;


/**
 * The SimpleClient class sends several messages to a
 * destination.
 */
public class SimpleClient {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Queue")
    private static Queue queue;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        TextMessage message = null;
        final int NUM_MSGS = 3;

        /*
         * Create connection.
         * Create session from connection; false means session is
         * not transacted.
         * Create sender and text message.
         * Send messages, varying text slightly.
         * Finally, close connection.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(queue);
            message = session.createTextMessage();

            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText(
                        "To komunikat numer " + (i + 1) + " z jupiterclient");
                System.out.println("Wysy³anie komunikatu: " + message.getText());
                producer.send(message);
            }
        } catch (JMSException e) {
            System.err.println("Wyst¹pi³ wyj¹tek: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }

            System.exit(0);
        }
    }
}
