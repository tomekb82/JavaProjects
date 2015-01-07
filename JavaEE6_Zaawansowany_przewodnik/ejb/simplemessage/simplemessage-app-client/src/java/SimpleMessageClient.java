/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


public class SimpleMessageClient {
    static final Logger logger = Logger.getLogger("SimpleMessageClient");
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;

    public static void main(String[] args) {
        Connection connection = null;
        Session session;
        MessageProducer messageProducer;
        TextMessage message;
        final int NUM_MSGS = 3;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            message = session.createTextMessage();

            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("To jest komunikat " + (i + 1));
                System.out.println("Wysy³anie komunikatu: " + message.getText());
                messageProducer.send(message);
            }

            System.out.println("Aby sprawdziæ, czy ziarno otrzyma³o komunikat,");
            System.out.println(
                    " sprawdŸ plik <install_dir>/domains/domain1/logs/server.log.");
        } catch (JMSException e) {
            logger.log(
                Level.SEVERE,
                "Wyst¹pi³ wyj¹tek: {0}",
                e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            } // if

            System.exit(0);
        } // finally
    } // main
} // class
