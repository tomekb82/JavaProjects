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
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;


/**
 * Klasa Producer zawiera tylko metod� main,
 * kt�ra wysy�a kilka komunikat�w do kolejki lub tematu.
 *
 * Uruchom program w po��czeniu z SynchConsumer lub
 * AsynchConsumer. Wska� "kolejka" lub "temat" w wierszu polece�
 * w momencie uruchomienia programu. Domy�lnie program
 * wysy�a tylko jeden komunikat. Podaj warto�� po nazwie
 * celu, by wys�a� wi�cej komunikat�w.
 */
public class Producer {
    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Queue")
    private static Queue queue;
    @Resource(lookup = "jms/Topic")
    private static Topic topic;

    /**
     * Metoda main.
     *
     * @param args     cel wykorzystywany przez przyk�ad
     *                 i opcjonalnie loczba komunikat�w
     *                 do wys�ania
     */
    public static void main(String[] args) {
        final int NUM_MSGS;
        Connection connection = null;

        if ((args.length < 1) || (args.length > 2)) {
            System.err.println(
                    "Program przyjmuje jeden lub dwa argumenty: "
                    + "<typ-celu> [<liczba-komunikat�w>]");
            System.exit(1);
        }

        String destType = args[0];
        System.out.println("Typ celu to " + destType);

        if (!(destType.equals("kolejka") || destType.equals("temat"))) {
            System.err.println("Argument musi by� typu \"kolejka\" lub " + "\"temat\"");
            System.exit(1);
        }

        if (args.length == 2) {
            NUM_MSGS = (new Integer(args[1])).intValue();
        } else {
            NUM_MSGS = 1;
        }

        Destination dest = null;

        try {
            if (destType.equals("kolejka")) {
                dest = (Destination) queue;
            } else {
                dest = (Destination) topic;
            }
        } catch (Exception e) {
            System.err.println("B��d wysy�ania: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * Tworzy po��czenie.
         * Tworzy sesj� z po��czenia; false oznacza, �e sesja
         * nie u�ywa transakcji.
         * Tworzy producenta i komunikat.
         * Wysy�a komunikaty o nieco r�nej tre�ci.
         * Wysy�a komunikat o zako�czeniu komunikat�w.
         * Na ko�cu zamyka po��czenie.
         */
        try {
            connection = connectionFactory.createConnection();

            Session session = connection.createSession(
                        false,
                        Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(dest);
            TextMessage message = session.createTextMessage();

            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText(
                        "Oto " + (i + 1) + ". komunikat od producenta");
                System.out.println("Wysy�anie komunikatu: " + message.getText());
                producer.send(message);
            }

            /*
             * Wys�anie komunikatu nietekstowego ko�czy komunikacj�.
             */
            producer.send(session.createMessage());
        } catch (JMSException e) {
            System.err.println("Wyst�pi� wyj�tek: " + e.toString());
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
