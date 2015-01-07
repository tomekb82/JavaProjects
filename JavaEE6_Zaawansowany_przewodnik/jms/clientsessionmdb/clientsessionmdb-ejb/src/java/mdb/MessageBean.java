/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * The MessageBean class is a message-driven bean.  It implements
 * the javax.jms.MessageListener interface. It is defined as public
 * (but not final or abstract).
 */
@MessageDriven(mappedName = "jms/Topic", activationConfig =  {
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "NewsType = 'Sport' OR NewsType = 'Opinie'")
    , @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    , @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MyID")
    , @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "MySub")
}
)
public class MessageBean implements MessageListener {
    static final Logger logger = Logger.getLogger("MessageBean");
    @Resource
    public MessageDrivenContext mdc;

    public MessageBean() {
    }

    /**
     * onMessage method, declared as public (but not final or
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a TextMessage and displays
     * the text.
     *
     * @param inMessage    the incoming message
     */
    @Override
    public void onMessage(Message inMessage) {
        TextMessage msg;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                logger.log(
                        Level.INFO,
                        "ZIARNO: otrzymano komunikat: {0}",
                        msg.getText());
            } else {
                logger.log(
                        Level.WARNING,
                        "Komunikat niewłaściwego typu: {0}",
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(
                    Level.SEVERE,
                    "MessageBean.onMessage: Wyjątek JMSException: {0}",
                    e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            logger.log(
                    Level.SEVERE,
                    "MessageBean.onMessage: Wyjątek: {0}",
                    te.toString());
        }
    }
}
