/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ManagedConnection;


/**
 * The connector architecture provides an event callback mechanism that
 * enables an application server to receive notifications from a
 * ManagedConnection instance. GlassFish Server implements this class in
 * order to listen to event notifications from ManagedConnection instances.
 */
public class JavaMailConnectionEventListener {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private ArrayList listeners;
    private ManagedConnection mcon;

    /**
     * Constructor.
     *
     * @param mcon  the managed connection that created this instance
     */
    public JavaMailConnectionEventListener(ManagedConnection mcon) {
        logger.info(
                " 3C.- Outbound:JavaMailConnectionEventListener::Constructor");
        listeners = new ArrayList();
        this.mcon = mcon;
    }

    /**
     * Sends a connection event to the application server.
     *
     * @param eventType  the ConnectionEvent type
     * @param ex  exception indicating a connection-related error
     * @param connectionHandle  the connection handle associated with the
     *                          ManagedConnection instance
     */
    public void sendEvent(
        int eventType,
        Exception ex,
        Object connectionHandle) {
        logger.info(
                "[Outbound:JavaMailConnectionEventListener]: Calling sendEvent");

        ArrayList list = (ArrayList) listeners.clone();
        ConnectionEvent ce = null;

        if (ex == null) {
            ce = new ConnectionEvent(mcon, eventType);
        } else {
            ce = new ConnectionEvent(mcon, eventType, ex);
        }

        if (connectionHandle != null) {
            ce.setConnectionHandle(connectionHandle);
        }

        for (int i = 0; i < list.size(); i++) {
            ConnectionEventListener l = (ConnectionEventListener) list.get(i);

            switch (eventType) {
            case ConnectionEvent.CONNECTION_CLOSED:
                l.connectionClosed(ce);

                break;

            case ConnectionEvent.LOCAL_TRANSACTION_STARTED:
                l.localTransactionStarted(ce);

                break;

            case ConnectionEvent.LOCAL_TRANSACTION_COMMITTED:
                l.localTransactionCommitted(ce);

                break;

            case ConnectionEvent.LOCAL_TRANSACTION_ROLLEDBACK:
                l.localTransactionRolledback(ce);

                break;

            case ConnectionEvent.CONNECTION_ERROR_OCCURRED:
                l.connectionErrorOccurred(ce);

                break;

            default:
                throw new IllegalArgumentException(
                        resource.getString("ILLEGAL_EVENT_TYPE") + eventType);
            }
        }
    }

    /**
     * Adds a connection event listener to the ManagedConnection Listener
     * instance. The registered ConnectionEventListener instances are notified
     * of connection close and error events and of local transaction-related
     * events on the ManagedConnection.
     *
     * @param listener  a new ConnectionEventListener to be registered
     */
    public void addConnectorListener(ConnectionEventListener listener) {
        logger.info(
                "[Outbound:JavaMailConnectionEventListener]: Calling addConnectorListener");
        listeners.add(listener);
    }

    /**
     * Removes an already registered connection event listener.
     *
     * @param listener the already registered connection event listener to be
     *                 removed
     */
    public void removeConnectorListener(ConnectionEventListener listener) {
        logger.info(
                "[Outbound:JavaMailConnectionEventListener]: Calling removeConnectorListener");

        if (listeners.contains(listener)) {
            boolean remove = listeners.remove(listener);
        }
    }
}
