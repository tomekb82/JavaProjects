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
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.io.InputStreamReader;
import java.io.IOException;


/**
 * Klasa SynchConsumer sk�ada si� jedynie z metody main,
 * kt�ra pobiera jeden lub wi�cej komunikat�w z kolejki lub tematu
 * w spos�b synchroniczny. Wykorzystuje klas� nas�uchiwania
 * komunikat�w TextListener. Uruchom program w po��czeniu z Producer.
 *
 * Wska� "kolejka" lub "temat" w wierszu polece�
 * w momencie uruchomienia programu. Aby zako�czy� program, wpisz
 * w wierszu polece� q lub Q.
 */
public class AsynchConsumer {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Queue")
    private static Queue queue;
    @Resource(lookup = "jms/Topic")
    private static Topic topic;

    /**
     * Metoda main.
     *
     * @param args     nazwa celu u�ywana przez przyk�ad
     */
    public static void main(String[] args) {
        String destType = null;
        Connection connection = null;
        Session session = null;
        Destination dest = null;
        MessageConsumer consumer = null;
        TextListener listener = null;
        TextMessage message = null;
        InputStreamReader inputStreamReader = null;
        char answer = '\0';

        if (args.length != 1) {
            System.err.println("Program przyjmuje jeden argument: <typ-celu>");
            System.exit(1);
        }

        destType = args[0];
        System.out.println("Typ celu to " + destType);

        if (!(destType.equals("kolejka") || destType.equals("temat"))) {
            System.err.println("Argument musi by� typu \"kolejka\" lub \"temat\"");
            System.exit(1);
        }

        try {
            if (destType.equals("kolejka")) {
                dest = (Destination) queue;
            } else {
                dest = (Destination) topic;
            }
        } catch (Exception e) {
            System.err.println("B��d ustawiania celu: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * Tworzy po��czenie.
         * Tworzy sesj� z po��czenia; false oznacza, �e sesja
         * nie u�ywa transakcji.
         * Tworzy konsumenta.
         * Rejestruje obiekt nas�uchiwania komunikat�w (TextListener).
         * Pobiera komunikaty z celu.
         * Po otrzymania wszystkich komunikat�w wpisz q lub Q.
         * Zamyka po��czenie.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(dest);
            listener = new TextListener();
            consumer.setMessageListener(listener);
            connection.start();
            System.out.println(
                    "By zako�czy� program, wpisz Q lub q i " + "naci�nij <return>");
            inputStreamReader = new InputStreamReader(System.in);

            while (!((answer == 'q') || (answer == 'Q'))) {
                try {
                    answer = (char) inputStreamReader.read();
                } catch (IOException e) {
                    System.err.println("Wyj�tek I/O: " + e.toString());
                }
            }
        } catch (JMSException e) {
            System.err.println("Wystapi� wyj�tek: " + e.toString());
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
