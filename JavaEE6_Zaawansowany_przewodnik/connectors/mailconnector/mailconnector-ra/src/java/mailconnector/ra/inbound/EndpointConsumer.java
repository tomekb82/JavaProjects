/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.resource.spi.endpoint.MessageEndpoint;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import mailconnector.api.JavaMailMessageListener;


/**
 * JavaMail Client RMI interface.
 *
 * This is a singleton class that represents the Client interface
 * used by the JavaMail Service.
 *
 * @author Alejandro Murillo
 */
public class EndpointConsumer {
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    public Method onMessage = null;
    ActivationSpecImpl activationSpec;
    MailServerFolder folder = null;
    MessageEndpointFactory endpointFactory;

    /**
     * Constructor. Creates a JavaMail Client Interface object and exports it
     * so that the server can access it.
     *
     * @param endpointFactory a MessageEndpointFactory
     * @param activationSpec  the activation spec
     */
    public EndpointConsumer(
        MessageEndpointFactory endpointFactory,
        ActivationSpecImpl activationSpec) throws Exception {
        logger.info("[Inbound:EndpointConsumer] Constructor");
        this.endpointFactory = endpointFactory;
        this.activationSpec = activationSpec;

        try {
            folder = new MailServerFolder(activationSpec);
        } catch (AuthenticationFailedException ie) {
            logger.log(
                    Level.INFO,
                    "[Inbound:EndpointConsumer] Authentication problem when opening Mail Folder: {0} Wrong password?",
                    getUniqueKey());
            throw ie;
        } catch (Exception ie) {
            logger.log(
                    Level.INFO,
                    "[Inbound:EndpointConsumer] Expected Error while opening Mail Folder: {0} -- "
                    + "missing foldername, hostname, username, or password",
                    getUniqueKey());
            throw ie;
        }

        logger.log(
                Level.INFO,
                "[EndpointConsumer] Created EndpointConsumer for: {0}",
                getUniqueKey());
    }

    /**
     * Delivers it to the appropriate EndPoint.
     *
     * @param message  the message to be delivered
     */
    public void deliverMessage(Message message) throws RemoteException {
        MessageEndpoint endpoint = null;
        Object[] args = { message };

        try {
            // o Create endpoint, passing XAResource.
            // o Call beforeDelivery to allow the appserver
            //   to engage delivery in transaction, if required.
            // o Deliver Message.
            if ((endpoint = endpointFactory.createEndpoint(null)) != null) {
                // If this was an XA capable RA then invoke 
                //  endpoint.beforeDelivery();
                logger.info("[Inbound:EndpointConsumer] Invoking onMessage");
                ((JavaMailMessageListener) endpoint).onMessage(message);
            }
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    "[Inbound:EndpointConsumer] deliverMessage.onmessageexception",
                    e.getMessage());
        } catch (Error error) {
            logger.log(
                    Level.WARNING,
                    "[Inbound:EndpointConsumer] deliverMessage.onmessageexception",
                    error.getMessage());
        } catch (Throwable t) {
            logger.log(
                    Level.WARNING,
                    "[Inbound:EndpointConsumer] deliverMessage.onmessageexception",
                    t.getMessage());
        } finally {
            // o Call afterDelivery to to permit the Application Server to 
            //   complete or rollback transaction on  delivery. This should 
            //   occur even if an exception has been thrown.
            // o Call release to indicate the endpoint can be recycled.
            if (endpoint != null) {
                //If this was an XA capable RA then invoke 
                //  endpoint.afterDelivery();
                endpoint.release();
            }
        }
    }

    public boolean hasNewMessages() throws Exception {
        logger.log(
                Level.INFO,
                "[Inbound:EndpointConsumer] Checking for new messages on: {0}",
                getUniqueKey());

        return folder.hasNewMessages();
    }

    public final String getUniqueKey() {
        logger.info("[Inbound:EndpointConsumer] Invoking getUniqueKey");

        return activationSpec.getUserName() + "-"
        + activationSpec.getFolderName() + "@" + activationSpec.getServerName();
    }

    public Message[] getNewMessages() {
        logger.info("[Inbound:EndpointConsumer] Invoking getNewMessages");

        Message[] msgs = null;

        try {
            msgs = folder.getNewMessages();

            if (msgs != null) {
                for (int i = 0; i < msgs.length; i++) {
                    if (!msgs[i].isSet(Flag.SEEN)) //Deliver only once
                     {
                        //deliverMessage(msgs[i]);
                        // Mark message as seen
                        msgs[i].setFlag(Flag.SEEN, true);
                    }
                }
            }
        } catch (Exception ie) {
            logger.severe(
                    "[InboundEndpointConsumer] getNewMessages caught an exception. Bailing out");
        }

        return msgs;
    }
}
