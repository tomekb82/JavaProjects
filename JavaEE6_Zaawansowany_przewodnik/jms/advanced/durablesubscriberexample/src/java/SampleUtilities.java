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
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.JMSException;


public class SampleUtilities {
    /**
     * Waits for 'count' messages on controlQueue before
     * continuing.  Called by a publisher to make sure that
     * subscribers have started before it begins publishing
     * messages.
     *
     * If controlQueue does not exist, the method throws an
     * exception.
     *
     * @param prefix    prefix (publisher or subscriber) to be
     *                  displayed
     * @param controlQueue   control queue
     * @param count     number of messages to receive
     */
    public static void receiveSynchronizeMessages(
        String prefix,
        ConnectionFactory connectionFactory,
        Queue controlQueue,
        int count) throws Exception {
        Connection connection = null;
        Session session = null;
        MessageConsumer receiver = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        } catch (Exception e) {
            System.err.println(
                    "Problem z po³¹czeniem w receiveSynchronizeMessages: "
                    + e.toString());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ee) {
                }
            }

            throw e;
        }

        try {
            System.out.println(
                    prefix + "Otrzymywanie komunikatów synchronizuj¹cych z "
                    + "kolejki steruj¹cej; iloœæ = " + count);
            receiver = session.createConsumer(controlQueue);

            while (count > 0) {
                receiver.receive();
                count--;
                System.out.println(
                        prefix + "Otrzymano komunikat synchronizuj¹cy; " + " oczekujê "
                        + count + " wiêcej");
            }
        } catch (JMSException e) {
            System.err.println("Wystapi³ wyj¹tek: " + e.toString());
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    /**
     * Sends a message to controlQueue.  Called by a subscriber
     * to notify a publisher that it is ready to receive
     * messages.
     * <p>
     * If controlQueue doesn't exist, the method throws an
     * exception.
     *
     * @param prefix    prefix (publisher or subscriber) to be
     *                  displayed
     * @param controlQueue  control queue
     */
    public static void sendSynchronizeMessage(
        String prefix,
        ConnectionFactory connectionFactory,
        Queue controlQueue) throws Exception {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        TextMessage message = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            System.err.println(
                    "Problem z po³¹czeniem w sendSynchronizeMessage: "
                    + e.toString());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ee) {
                }
            }

            throw e;
        }

        try {
            producer = session.createProducer(controlQueue);
            message = session.createTextMessage();
            message.setText("synchronize");
            System.out.println(
                    prefix + "Wysy³anie komunikatów steruj¹cych do "
                    + "kolejki steruj¹cej");
            producer.send(message);
        } catch (JMSException e) {
            System.err.println("Wyst¹pi³ wyj¹tek: " + e.toString());
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    /**
     * Monitor class for asynchronous examples.  Producer signals
     * end of message stream; listener calls allDone() to notify
     * consumer that the signal has arrived, while consumer calls
     * waitTillDone() to wait for this notification.
     */
    public static class DoneLatch {
        boolean done = false;

        /**
         * Waits until done is set to true.
         */
        public void waitTillDone() {
            synchronized (this) {
                while (!done) {
                    try {
                        this.wait();
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        /**
         * Sets done to true.
         */
        public void allDone() {
            synchronized (this) {
                done = true;
                this.notify();
            }
        }
    }
}
