/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkManager;
import javax.resource.spi.work.WorkRejectedException;
import javax.transaction.xa.XAResource;
import mailconnector.api.JavaMailMessageListener;
import mailconnector.ra.inbound.ActivationSpecImpl;
import mailconnector.ra.inbound.EndpointConsumer;
import mailconnector.ra.inbound.PollingThread;


/**
 * JavaMail Resource Adapter
 *
 * @author Alejandro E. Murillo
 */
@Connector(description = "Sample adapter using the JavaMail API", displayName = "InboundResourceAdapter", vendorName = "Java EE Tutorial", eisType = "MAIL", version = "6", authMechanisms =  {
    @AuthenticationMechanism(authMechanism = "BasicPassword", credentialInterface = AuthenticationMechanism.CredentialInterface.PasswordCredential)
} /*
 * Since the following attribute values denote the default values of the
 * annotation, they need not be specified explicitly.
 *
 * transactionSupport =
 * TransactionSupport.TransactionSupportLevel.NoTransaction,
 * reauthenticationSupport = false
 */
)
public class ResourceAdapterImpl implements ResourceAdapter, Serializable {
    private static final long serialVersionUID = -9047363709295027615L;
    static final Logger logger = Logger.getLogger("mailconnector.ra");
    public transient Context jndiContext = null;
    public Method onMessage = null;
    protected transient BootstrapContext bootCtx;
    protected transient WorkManager workManager;
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private Work pollingThread;

    /**
     * Constructor.
     */
    public ResourceAdapterImpl() {
    }

    /**
     * Called by the application server to initialize the Resource Adapter.
     *
     * @param ctx the BootstrapContext
     */
    @Override
    public void start(BootstrapContext ctx)
        throws ResourceAdapterInternalException {
        logger.info("[ResourceAdapterImpl] Entering start");

        /*
         * Bootstrap context - used to acquire WorkManager, Timer, or
         * XATerminator
         */
        this.bootCtx = ctx;

        try {
            // Get the initial JNDI Context
            this.jndiContext = new InitialContext();

            // Get Work Manager
            this.workManager = ctx.getWorkManager();
        } catch (Exception ex) {
            logger.severe(resource.getString("resourceadapterimpl.noservice"));
            throw new ResourceAdapterInternalException(
                    resource.getString("resourceadapterimpl.noservice"));
        }

        setOnMessageMethod();

        /*
         * Start the polling thread
         */
        try {
            pollingThread = new PollingThread(workManager);
            workManager.scheduleWork(pollingThread);
        } catch (WorkRejectedException ex) {
            throw new ResourceAdapterInternalException(
                    MessageFormat.format(
                            resource.getString(
                                    "resourceadapterimpl.worker_activation_rejected"),
                            new Object[] { ex.getMessage() }),
                    ex);
        } catch (Exception ex) {
            throw new ResourceAdapterInternalException(
                    MessageFormat.format(
                            resource.getString(
                                    "resourceadapterimpl.worker_activation_failed"),
                            new Object[] { ex.getMessage() }),
                    ex);
        }
    }

    /**
     * Sets the method for the onMessage method used in MessageListener.
     */
    private void setOnMessageMethod() {
        logger.info("[ResourceAdapterImpl] Entering setOnMessageMethod");

        Method onMessageMethod = null;

        try {
            Class msgListenerClass = JavaMailMessageListener.class;
            Class[] paramTypes = { Message.class };
            onMessageMethod = msgListenerClass.getMethod(
                        "onMessage",
                        paramTypes);
        } catch (NoSuchMethodException ex) {
            logger.severe(ex.getMessage());
        }

        onMessage = onMessageMethod;
    }

    /**
     * Gets the method used to deliver messages.
     *
     * @return the onMessage method
     */
    public Method getOnMessageMethod() {
        return onMessage;
    }

    /**
     * Called by the application server to indicate shutdown is imminent. The
     * application server should have undeployed all the message endpoints prior
     * to this call, but the JavaMail-RA will iterate through them and ensure
     * that all the message endpoints are no longer consuming messages.
     */
    @Override
    public void stop() {
        logger.info("[ResourceAdapterImpl.stop()] Stopping the polling thread");

        try {
            ((PollingThread) pollingThread).stopPolling();
        } catch (Exception ex) {
            logger.severe(resource.getString("resourceadapterimpl.noservice"));
        }
    }

    /**
     * Called by the application server when a message-driven bean
     * (MessageEndpoint) is deployed. Causes the resource adapter instance to do
     * the necessary setup (setting up message delivery for the message endpoint
     * with a message provider).
     *
     * @param endpointFactory a message endpoint factory instance
     * @param spec an ActivationSpec instance
     *
     * @exception NotSupportedException if message endpoint activation is
     * rejected because of incorrect activation setup information
     */
    @Override
    public void endpointActivation(
        MessageEndpointFactory endpointFactory,
        ActivationSpec spec) throws NotSupportedException {
        logger.info("[ResourceAdapterImpl.endpointActivation()] Entered");

        try {
            EndpointConsumer ec = new EndpointConsumer(
                        endpointFactory,
                        (ActivationSpecImpl) spec);
            ((PollingThread) pollingThread).addEndpointConsumer(
                    endpointFactory,
                    ec);
        } catch (Exception ex) {
            logger.finest(
                    "[RA.endpointActivation()] An exception was caught while activating the endpoint");
            logger.finest(
                    "[RA.endpointActivation()] Please check the server logs for details");

            NotSupportedException newEx = new NotSupportedException(
                        MessageFormat.format(
                                resource.getString(
                                        "resourceadapterimpl.endpoint_activation_fail"),
                                new Object[] { ex.getMessage() }));
            newEx.initCause(ex);
        }
    }

    /**
     * Called by the application server when the MessageEndpoint (message-driven
     * bean) is undeployed. The instance passed as argument to this method call
     * should be identical to that passed in for the corresponding
     * endpointActivation call. This causes the resource adapter to stop
     * delivering messages to the message endpoint.
     *
     * @param endpointFactory a message endpoint factory instance
     * @param spec an activation spec instance
     */
    @Override
    public void endpointDeactivation(
        MessageEndpointFactory endpointFactory,
        ActivationSpec spec) {
        logger.info("[ResourceAdapterImpl.endpointdeactivation()] Entered");

        ((PollingThread) pollingThread).removeEndpointConsumer(endpointFactory);
    }

    /**
     * This method is called by the Application Server on the restart of the
     * Application Server when there are potential pending transactions. For
     * example, it may be called after a server crash. The Application Server
     * requests the XA Resources that correspond to the Activation Specs for the
     * endpoints that it is restarting. It may use those XA Resources to
     * determine transaction status and attempt to commit or rollback.
     *
     * Because this implementation does not support transactions, this method
     * does nothing.
     *
     * @param specs an array of ActivationSpec objects
     *
     * @return an XAResource
     */
    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs)
        throws ResourceException {
        /*
         * Do nothing
         */
        return null;
    }
}
