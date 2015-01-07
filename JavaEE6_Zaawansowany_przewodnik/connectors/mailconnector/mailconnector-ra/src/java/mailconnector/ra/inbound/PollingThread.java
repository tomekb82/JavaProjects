/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.resource.NotSupportedException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkManager;
import javax.resource.spi.work.WorkRejectedException;


/**
 *
 * @author Alejandro Murillo
 *
 */
public class PollingThread implements Work {
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    private static int QUANTUM = 30; // 30 Seconds
    protected transient WorkManager workManager;
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private transient HashMap endpointConsumers = null;
    private boolean active = false;

    /**
     * Constructor.
     */
    public PollingThread(WorkManager workManager) {
        this.active = true;
        this.workManager = workManager;

        /*
         * Set up the hash tables for the use of the resource adapter. These
         * tables hold references to MessageEndpointFactory and
         * endpointConsumers. The factoryToConsumer table links the Message
         * factory id to the Consumer Id.
         */
        endpointConsumers = new HashMap(10);

        logger.info("[Inbound:PollingThread::Constructor] Leaving");
    }

    /**
     * release: called by the WorkerManager
     */
    @Override
    public void release() {
        logger.info(
                "[Inbound:PollingThread] Worker Manager called release for PollingThread");
        active = false;
    }

    /**
     * run
     */
    @Override
    public void run() {
        logger.info(
                "[Inbound:PollingThread] WorkManager started polling thread");

        while (active) {
            try {
                pollEndpoints();
                Thread.sleep(QUANTUM * 1000L);
            } catch (Exception e) {
                logger.severe(e.getLocalizedMessage());
            }
        }

        logger.info("[Inbound:PollingThread] Polling Thread Leaving");
    }

    private void pollEndpoints() {
        logger.info("[Inbound:PollingThread] Calling pollEndpoints");

        synchronized (endpointConsumers) {
            Collection consumers = endpointConsumers.entrySet();

            if (consumers != null) {
                Iterator iter = consumers.iterator();

                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    EndpointConsumer ec = (EndpointConsumer) entry.getValue();

                    try {
                        if (ec.hasNewMessages()) {
                            Message[] messages = ec.getNewMessages();

                            if (messages != null) {
                                for (Message msg : messages) {
                                    scheduleMessageDeliveryThread(ec, msg);
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.severe(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * @param message the message to be delivered
     */
    private void scheduleMessageDeliveryThread(
        EndpointConsumer ec,
        Message msg) throws Exception {
        logger.log(
                Level.INFO,
                "[Inbound:PollingThread] scheduling a delivery FROM: {0}",
                ec.getUniqueKey());

        try {
            Work deliveryThread = (Work) new DeliveryThread(ec, msg);
            workManager.scheduleWork(deliveryThread);
        } catch (WorkRejectedException ex) {
            throw new NotSupportedException(
                    MessageFormat.format(
                            resource.getString(
                                    "resourceadapterimpl.worker_activation_rejected"),
                            new Object[] { ex.getMessage() }),
                    ex);
        } catch (Exception ex) {
            throw new NotSupportedException(
                    MessageFormat.format(
                            resource.getString(
                                    "resourceadapterimpl.worker_activation_failed"),
                            new Object[] { ex.getMessage() }),
                    ex);
        }
    }

    public void stopPolling() {
        removeAllEndpointConsumers();
        this.active = false;
    }

    public void addEndpointConsumer(
        MessageEndpointFactory endpointFactory,
        EndpointConsumer ec) {
        logger.info("[Inbound:PollingThread.addEndpointConsumer()] Entered");

        synchronized (endpointConsumers) {
            endpointConsumers.put(endpointFactory, ec);
        }
    }

    public void removeEndpointConsumer(MessageEndpointFactory endpointFactory) {
        logger.info("[Inbound:PollingThread.removeEndpointConsumer()] Entered");

        EndpointConsumer ec = (EndpointConsumer) endpointConsumers.get(
                    endpointFactory);

        synchronized (endpointConsumers) {
            endpointConsumers.remove(ec);
        }
    }

    /**
     * Iterates through the endpointConsumers, shutting them down and preparing
     * for stopping the Resource Adapter.
     */
    private void removeAllEndpointConsumers() {
        logger.info(
                "[Inbound:PollingThread.removeAllEndpointConsumers()] Entered");

        synchronized (endpointConsumers) {
            Collection consumers = endpointConsumers.entrySet();

            if (consumers != null) {
                Iterator iter = consumers.iterator();

                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    EndpointConsumer ec = (EndpointConsumer) entry.getValue();

                    try {
                        endpointConsumers.remove(ec);
                    } catch (Exception e) {
                        logger.severe(e.getMessage());
                    }
                }
            }
        }

        endpointConsumers = null;
    }
}
