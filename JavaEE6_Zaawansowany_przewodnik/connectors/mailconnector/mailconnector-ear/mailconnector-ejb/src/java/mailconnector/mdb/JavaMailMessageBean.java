/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.mdb;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.mail.Message;
import mailconnector.api.JavaMailMessageListener;


/**
 * A simple message-driven bean that implements JavaMailMessageListener. The
 * @MessageDriven annotation's activationConfig element is commented out,
 * because the properties are specified elsewhere.
 */
@MessageDriven
public class JavaMailMessageBean implements JavaMailMessageListener {
    static final Logger logger = Logger.getLogger("mailconnector.mdb");

    /**
     * Default constructor. Creates a bean.
     */
    public JavaMailMessageBean() {
        logger.info("[MDB] In JavaMailMessageBean.JavaMailMessageBean()");
    }

    /**
     * When the inbox receives a message, the EJB container invokes the
     * <code>onMessage</code> method of the message-driven bean.
     *
     * @param message incoming message
     */
    @Override
    public void onMessage(Message message) {
        logger.info("[MDB] In JavaMailMessageBean.onMessage");
    }
}
