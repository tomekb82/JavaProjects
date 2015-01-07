/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.LocalTransaction;
import javax.resource.spi.ConnectionEvent;
import mailconnector.api.JavaMailConnection;


/**
 * Application-level connection handle that is used by a client component to
 * access an EIS instance.
 */
public class JavaMailConnectionImpl implements JavaMailConnection {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private Folder folder; // Folder 1x1 with connection
    private ManagedConnectionImpl mc; // if mc is null, connection is invalid

    /**
     * Constructor.
     *
     * @param mc a physical connection to an underlying EIS
     * @param connectionInfo connection-specific info/properties
     *
     */
    JavaMailConnectionImpl(
        ManagedConnectionImpl mc,
        Folder folder) {
        this.mc = mc;
        this.folder = folder;
        logger.info(" 5.- Outbound:JavaMailConnectionImpl::Constructor");
    }

    /**
     * Retrieves a ManagedConnection.
     *
     * @return a ManagedConnection instance representing the physical connection
     * to the EIS
     */
    public ManagedConnectionImpl getManagedConnection() {
        logger.log(
                Level.INFO,
                " -- Outbound:JavaMailConnectionImpl::getManagedConnection mc={0}",
                mc);

        return mc;
    }

    /**
     * Returns a javax.resource.cci.LocalTransaction instance that enables a
     * component to demarcate resource manager local transactions on the
     * Connection.
     *
     * Because this implementation does not support transactions, the method
     * throws an exception.
     *
     * @return a LocalTransaction instance
     */
    public LocalTransaction getLocalTransaction() throws ResourceException {
        throw new ResourceException(resource.getString("NO_TRANSACTION"));
    }

    /**
     * Returns the metadata for the ManagedConnection.
     *
     * @return a ConnectionMetaData instance
     */
    public ConnectionMetaData getMetaData() throws ResourceException {
        logger.log(
                Level.INFO,
                " -- Outbound:JavaMailConnectionImpl:: getMetaData mc={0}",
                mc);

        return new ConnectionMetaDataImpl(mc);
    }

    /**
     * Closes the connection.
     */
    @Override
    public void close() throws ResourceException {
        logger.log(
                Level.INFO,
                " -- Outbound:JavaMailConnectionImpl:: close mc={0}",
                mc);

        if (mc == null) {
            return; // connection is already closed
        }

        mc.removeJavaMailConnection(this);

        // Send a close event to the App Server
        mc.sendEvent(ConnectionEvent.CONNECTION_CLOSED, null, this);
        mc = null;
    }

    /**
     * Associates connection handle with new managed connection.
     *
     * @param newMc new managed connection
     */
    public void associateConnection(ManagedConnectionImpl newMc)
        throws ResourceException {
        logger.info(
                "[Outbound:JavaMailConnectionImpl] Calling associateConnection");
        checkIfValid();
        // dissociate handle from current managed connection
        mc.removeJavaMailConnection(this);
        // associate handle with new managed connection
        newMc.addJavaMailConnection(this);
        mc = newMc;
    }

    /**
     * Checks the validity of the physical connection to the EIS.
     */
    void checkIfValid() throws ResourceException {
        logger.log(
                Level.INFO,
                " -- Outbound:JavaMailConnectionImpl::checkIfValid mc={0}",
                mc);

        if (mc == null) {
            throw new ResourceException(
                    resource.getString("INVALID_CONNECTION"));
        }
    }

    /**
     * Sets the physical connection to the EIS as invalid. The physical
     * connection to the EIS cannot be used any more.
     */
    void invalidate() {
        logger.log(
                Level.INFO,
                " -- Outbound:JavaMailConnectionImpl::invalidate mc={0}",
                mc);
        mc = null;
    }

    /**
     * Application-specific method. Fetches new messages from the mail server.
     *
     * @return an array of messages
     */
    @Override
    public Message[] getNewMessages() throws ResourceException {
        logger.info("[Outbound:JavaMailConnectionImpl] Calling getNewMessages");
        checkIfValid();

        try {
            return mc.getNewMessages(folder);
        } catch (Exception ex) {
            logger.log(
                    Level.WARNING,
                    "Outbound:JavaMailConnectionImpl::getNewMessages threw exception: {0}",
                    ex);
            throw new ResourceException(ex.getMessage());
        }
    }

    /**
     * Application-specific method. Fetches new message headers from the mail
     * server.
     *
     * @return a String array of message headers
     */
    @Override
    public String[] getNewMessageHeaders() throws ResourceException {
        logger.info(
                "[Outbound:JavaMailConnectionImpl] Calling getNewMessageHeaders");
        checkIfValid();

        try {
            return mc.getNewMessageHeaders(folder);
        } catch (Exception ex) {
            logger.log(
                    Level.WARNING,
                    "Outbound:ManagedConnectionImpl::getNewMessageHeaders threw exception: {0}",
                    ex);
            throw new ResourceException(ex.getMessage());
        }
    }
}
