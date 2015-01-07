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
 * Klasa Producer zawiera tylko metodê main,
 * która wysy³a kilka komunikatów do kolejki lub tematu.
 *
 * Uruchom program w po³¹czeniu z SynchConsumer lub
 * AsynchConsumer. Wska¿ "kolejka" lub "temat" w wierszu poleceñ
 * w momencie uruchomienia programu. Domyœlnie program
 * wysy³a tylko jeden komunikat. Podaj wartoœæ po nazwie
 * celu, by wys³aæ wiêcej komunikatów.
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
     * @param args     cel wykorzystywany przez przyk³ad
     *                 i opcjonalnie loczba komunikatów
     *                 do wys³ania
     */
    public static void main(String[] args) {
        final int NUM_MSGS;
        Connection connection = null;

        if ((args.length < 1) || (args.length > 2)) {
            System.err.println(
                    "Program przyjmuje jeden lub dwa argumenty: "
                    + "<typ-celu> [<liczba-komunikatów>]");
            System.exit(1);
        }

        String destType = args[0];
        System.out.println("Typ celu to " + destType);

        if (!(destType.equals("kolejka") || destType.equals("temat"))) {
            System.err.println("Argument musi byæ typu \"kolejka\" lub " + "\"temat\"");
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
            System.err.println("B³¹d wysy³ania: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * Tworzy po³¹czenie.
         * Tworzy sesjê z po³¹czenia; false oznacza, ¿e sesja
         * nie u¿ywa transakcji.
         * Tworzy producenta i komunikat.
         * Wysy³a komunikaty o nieco ró¿nej treœci.
         * Wysy³a komunikat o zakoñczeniu komunikatów.
         * Na koñcu zamyka po³¹czenie.
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
                System.out.println("Wysy³anie komunikatu: " + message.getText());
                producer.send(message);
            }

            /*
             * Wys³anie komunikatu nietekstowego koñczy komunikacjê.
             */
            producer.send(session.createMessage());
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
