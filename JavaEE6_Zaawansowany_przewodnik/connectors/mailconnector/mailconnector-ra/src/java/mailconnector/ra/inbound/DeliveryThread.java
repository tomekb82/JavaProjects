/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkContext;
import javax.resource.spi.work.WorkContextProvider;


/**
 *
 * @author Alejandro Murillo
 *
 */
public class DeliveryThread implements Work, WorkContextProvider {
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private EndpointConsumer endpointConsumer;
    private List<WorkContext> workContexts = new ArrayList<WorkContext>();
    private Message msg;

    /**
     * Constructor.
     */
    public DeliveryThread(
        EndpointConsumer endpointConsumer,
        Message msg) {
        this.endpointConsumer = endpointConsumer;
        this.msg = msg;
        initializeWorkContexts(msg);
        logger.info("[Inbound:DeliveryThread::Constructor] Leaving");
    }

    /**
     * release: called by the WorkerManager
     */
    @Override
    public void release() {
        logger.info(
                "[Inbound:DeliveryThread] Worker Manager called release for deliveryThread ");
    }

    /**
     * run
     */
    @Override
    public void run() {
        logger.info(
                "[Inbound:DeliveryThread] WorkManager started delivery thread ");

        try {
            endpointConsumer.deliverMessage(msg);
        } catch (Exception te) {
            logger.log(
                    Level.SEVERE,
                    "Inbound:DeliveryThread::run got an exception: {0}",
                    te.getMessage());
        }

        logger.info("[Inbound:DeliveryThread] DeliveryThread Leaving");
    }

    @Override
    public List<WorkContext> getWorkContexts() {
        return workContexts;
    }

    private void initializeWorkContexts(Message msg) {
        try {
            logger.info(
                    "[Inbound:DeliveryThread] Calling initializeWorkContexts");

            Address[] recipients = msg.getFrom();

            if ((recipients != null) && (recipients.length > 0)) {
                // Let us consider first recipient alone.
                Address recipient = recipients[0];
                String recipientId = recipient.toString();

                if (recipientId.indexOf("@") > 0) {
                    recipientId = recipientId.substring(
                                0,
                                recipientId.indexOf("@"));
                }

                //Assuming that the password is same as username
                MySecurityContext mysc = new MySecurityContext(
                            recipientId,
                            recipientId,
                            recipientId);
                getWorkContexts()
                    .add(mysc);
            }
        } catch (MessagingException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
