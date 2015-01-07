/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.QueueBrowser;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.Enumeration;


/**
 * Klasa MessageBrowser sprawdza kolejkê i wyœwietla przechowywane
 * w niej komunikaty.
 */
public class MessageBrowser {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Queue")
    private static Queue queue;

    /**
     * Metoda main.
     *
     * @param args     kolejka wykorzystywana przez przyk³ad
     */
    public static void main(String[] args) {
        Connection connection = null;

        /*
         * Tworzy po³¹czenie.
         * Tworzy sesjê z po³¹czenia; false oznacza, ¿e sesja
         * nie u¿ywa transakcji.
         * Tworzy QueueBrowser.
         * Sprawdza komunikaty znajduj¹ce siê w kolejce.
         * Zamyka po³¹czenie.
         */
        try {
            connection = connectionFactory.createConnection();

            Session session = connection.createSession(
                        false,
                        Session.AUTO_ACKNOWLEDGE);
            QueueBrowser browser = session.createBrowser(queue);
            Enumeration msgs = browser.getEnumeration();

            if (!msgs.hasMoreElements()) {
                System.out.println("W kolejce nie ma komunikatów");
            } else {
                while (msgs.hasMoreElements()) {
                    Message tempMsg = (Message) msgs.nextElement();
                    System.out.println("Komunikat: " + tempMsg);
                }
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
        }
    }
}
