/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;


/**
 * Klasa TextListener implementuje interfejs MessageListener,
 * definiuj�c metod� onMessage method, kt�ra wy�wietla
 * zawarto�� TextMessage.
 *
 * Klasa jest wykorzystywana przez klas� SimpleAsynchConsumer.
 */
public class TextListener implements MessageListener {
    /**
     * Rzutuje komunikat na TextMessage i wy�wietla jego zawarto��.
     *
     * @param message     przychodz�cy komunikat
     */
    public void onMessage(Message message) {
        TextMessage msg = null;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Otrzymano komunikat: " + msg.getText());
            } else {
                System.err.println("Komunikat nie jest typu TextMessage");
            }
        } catch (JMSException e) {
            System.err.println("Wyj�tek JMSException w onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.err.println("Wyj�tek w onMessage():" + t.getMessage());
        }
    }
}
