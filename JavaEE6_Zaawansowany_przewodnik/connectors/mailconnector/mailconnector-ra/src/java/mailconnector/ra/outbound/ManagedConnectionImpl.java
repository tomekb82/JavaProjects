/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.IllegalStateException;
import javax.resource.spi.SecurityException;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;
import mailconnector.api.JavaMailConnection;
import mailconnector.share.ConnectionSpecImpl;


/**
 * The ManagedConnectionImpl class represents a physical connection to the
 * backend Mail Server.
 */
public class ManagedConnectionImpl implements ManagedConnection {
    private static int testCounter = 0;
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private JavaMailConnectionEventListener eventListener;
    private MailServerStore store = null; //Several connections fro a store
    private ManagedConnectionFactoryImpl mcf;
    private PasswordCredential passCred = null;
    private PrintWriter logWriter;
    private Set connectionSet; // set of Mail Server Connections
    private boolean destroyed;
    private int myId = 0;

    /**
     * Constructor.
     *
     * @param mcf the ManagedConnectionFactory that created this instance
     * @param subject security context as JAAS subject
     * @param cxRequestInfo ConnectionRequestInfo instance
     */
    ManagedConnectionImpl(
        ManagedConnectionFactoryImpl mcf,
        Subject subject,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        myId = testCounter++;
        logger.log(
                Level.INFO,
                " 3B.- ({0}) Outbound:ManagedConnectionImpl::constructor",
                myId);
        this.mcf = mcf;

        // Note: this will select the credential that matches this MC's MCF.
        // The credential's MCF is set by the application server.
        this.passCred = Util.getPasswordCredential(mcf, subject, cxRequestInfo);

        // Open the physical connection to a store
        openStore(cxRequestInfo);

        connectionSet = new HashSet();
        eventListener = new JavaMailConnectionEventListener(this);
    }

    /**
     * Creates a new connection handle to the Mail Server represented by the
     * ManagedConnection instance. This connection handle is used by the
     * application code to refer to the underlying physical connection.
     *
     * @param subject security context as JAAS subject
     * @param cxRequestInfo ConnectionRequestInfo instance
     *
     * @return Connection instance representing the connection handle
     *
     * @exception ResourceException if the method fails to get a connection
     */
    @Override
    public Object getConnection(
        Subject subject,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        logger.log(
                Level.INFO,
                " 4.- ({0}) Outbound:ManagedConnectionImpl::getConnection: "
                + "ConnectionManager requested a Connection handle",
                myId);

        checkIfDestroyed();

        PasswordCredential pc = Util.getPasswordCredential(
                    mcf,
                    subject,
                    cxRequestInfo);

        if (!Util.isPasswordCredentialEqual(pc, passCred)) {
            throw new SecurityException(
                    resource.getString("PRINCIPAL_DOES_NOT_MATCH"));
        }

        // We only need the Folder name as all the connections share the store
        String folderName;

        if (cxRequestInfo != null) {
            folderName = ((ConnectionRequestInfoImpl) cxRequestInfo)
                .getFolderName();
        } else {
            // Use default values
            folderName = mcf.getFolderName();
        }

        Folder folder;

        try {
            folder = store.getFolder(folderName);
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    "Outbound:ManagedConnectionImpl::getConnection threw exception: {0}",
                    e);
            throw new ResourceException(e.getMessage());
        }

        JavaMailConnection javaMailCon = new JavaMailConnectionImpl(
                    this,
                    folder);
        addJavaMailConnection(javaMailCon);

        return javaMailCon;
    }

    /**
     * Destroys the physical connection.
     *
     * @exception ResourceException if the method fails to destroy the
     * connection
     */
    @Override
    public void destroy() throws ResourceException {
        logger.log(
                Level.INFO,
                " 9.- ({0}) Outbound:ManagedConnectionImpl::destroy called",
                myId);

        if (destroyed) {
            return;
        }

        destroyed = true;

        testCounter--;

        invalidateJavaMailConnections();

        try {
            store.closeStore();
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    "Outbound:ManagedConnectionImpl::destroy threw exception: {0}",
                    e);
            throw new ResourceException(e.getMessage());
        }
    }

    /**
     * Initiates a cleanup of the client-specific state maintained by a
     * ManagedConnection instance. The cleanup should invalidate all connection
     * handles created using this ManagedConnection instance.
     *
     * @exception ResourceException if the cleanup fails
     */
    @Override
    public void cleanup() throws ResourceException {
        checkIfDestroyed();

        logger.log(
                Level.INFO,
                " 8.- ({0}) Outbound:ManagedConnectionImpl::cleanup called",
                myId);

        invalidateJavaMailConnections();
    }

    private void invalidateJavaMailConnections() {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling invalidateJavaMailConnections");

        Iterator it = connectionSet.iterator();

        while (it.hasNext()) {
            JavaMailConnectionImpl javaMailCon = (JavaMailConnectionImpl) it
                .next();
            javaMailCon.invalidate();
        }

        connectionSet.clear();
    }

    /**
     * Used by the container to change the association of an application-level
     * connection handle with a ManagedConnection instance. The container should
     * find the right ManagedConnection instance and call the
     * associateConnection method.
     *
     * @param connection application-level connection handle
     *
     * @exception ResourceException if the attempt to change the association
     * fails
     */
    @Override
    public void associateConnection(Object connection)
        throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling associateConnection");
        checkIfDestroyed();

        if (connection instanceof JavaMailConnection) {
            JavaMailConnectionImpl javaMailCon = (JavaMailConnectionImpl) connection;
            javaMailCon.associateConnection(this);
        } else {
            throw new IllegalStateException(
                    resource.getString("INVALID_CONNECTION"));
        }
    }

    /**
     * Adds a connection event listener to the ManagedConnection instance. The
     * registered ConnectionEventListener instances are notified of connection
     * close and error events as well as local-transaction-related events on the
     * Managed Connection.
     *
     * @param listener a new ConnectionEventListener to be registered
     */
    @Override
    public void addConnectionEventListener(ConnectionEventListener listener) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling addConnectionEventListener");
        eventListener.addConnectorListener(listener);
    }

    /**
     * Removes an already registered connection event listener from the
     * ManagedConnection instance.
     *
     * @param listener already registered connection event listener to be
     * removed
     */
    @Override
    public void removeConnectionEventListener(ConnectionEventListener listener) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling removeConnectionEventListener");
        eventListener.removeConnectorListener(listener);
    }

    /**
     * Returns a javax.transaction.xa.XAresource instance. An application server
     * enlists this XAResource instance with the Transaction Manager if the
     * ManagedConnection instance is being used in a JTA transaction that is
     * being coordinated by the Transaction Manager.
     *
     * Because this implementation does not support transactions, the method
     * throws an exception.
     *
     * @return the XAResource instance
     *
     * @exception ResourceException if transactions are not supported
     */
    @Override
    public XAResource getXAResource() throws ResourceException {
        logger.info("[Outbound:ManagedConnectionImpl] Calling getXAResource");
        throw new NotSupportedException(resource.getString("NO_XATRANSACTION"));
    }

    /**
     * Returns a javax.resource.spi.LocalTransaction instance. The
     * LocalTransaction interface is used by the container to manage local
     * transactions for a RM instance.
     *
     * Because this implementation does not support transactions, the method
     * throws an exception.
     *
     * @return javax.resource.spi.LocalTransaction instance
     *
     * @exception ResourceException if transactions are not supported
     */
    @Override
    public LocalTransaction getLocalTransaction() throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling getLocalTransaction");
        throw new NotSupportedException(resource.getString("NO_TRANSACTION"));
    }

    /**
     * Gets the metadata information for this connection's underlying EIS
     * resource manager instance. The ManagedConnectionMetaData interface
     * provides information about the underlying EIS instance associated with
     * the ManagedConnection instance.
     *
     * @return ManagedConnectionMetaData ManagedConnectionMetaData instance
     *
     * @exception ResourceException if the metadata cannot be retrieved
     */
    @Override
    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        logger.info("[Outbound:ManagedConnectionImpl] Calling getMetaData");
        checkIfDestroyed();

        return new ManagedConnectionMetaDataImpl(this);
    }

    /**
     * Sets the log writer for this ManagedConnection instance. The log writer
     * is a character output stream to which all logging and tracing messages
     * for this ManagedConnection instance will be printed.
     *
     * @param out character output stream to be associated
     *
     * @exception ResourceException if the method fails
     */
    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.logWriter = out;
    }

    /**
     * Gets the log writer for this ManagedConnection instance.
     *
     * @return the character output stream associated with this
     * ManagedConnection instance
     *
     * @exception ResourceException if the method fails
     */
    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logWriter;
    }

    /**
     * Gets the user name of the user associated with the ManagedConnection
     * instance.
     *
     * @return the username for this connection
     */
    public String getUserName() {
        logger.info("[Outbound:ManagedConnectionImpl] Calling getUserName");

        if (passCred != null) {
            return passCred.getUserName();
        } else {
            return null;
        }
    }

    /**
     * Gets the password for the user associated with the ManagedConnection
     * instance.
     *
     * @return the password for this connection
     */
    public PasswordCredential getPasswordCredential() {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling getPasswordCredential");

        return passCred;
    }

    /**
     * Associate connection handle with the physical connection.
     *
     * @param javaMailCon connection handle
     */
    public void addJavaMailConnection(JavaMailConnection javaMailCon) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling addJavaMailConnection");
        connectionSet.add(javaMailCon);
    }

    /**
     * Check validation of the physical connection.
     *
     * @exception ResourceException if the connection has been destroyed
     */
    private void checkIfDestroyed() throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling checkIfDestroyed");

        if (destroyed) {
            throw new IllegalStateException(
                    resource.getString("DESTROYED_CONNECTION"));
        }
    }

    /**
     * Removes the associated connection handle from the connections set to the
     * physical connection.
     *
     * @param javaMailCon the connection handle
     */
    public void removeJavaMailConnection(JavaMailConnection javaMailCon) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling removeJavaMailConnection");
        connectionSet.remove(javaMailCon);
    }

    /**
     * Checks validation of the physical connection.
     *
     * @return true if the connection has been destroyed; false otherwise
     */
    boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Returns the ManagedConnectionFactory that created this instance of
     * ManagedConnection.
     *
     * @return the ManagedConnectionFactory for this connection
     */
    public ManagedConnectionFactoryImpl getManagedConnectionFactory() {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling getManagedConnectionFactory");

        return this.mcf;
    }

    /**
     * Sends a connection event to the application server.
     *
     * @param eventType the ConnectionEvent type
     * @param ex exception indicating a connection-related error
     */
    public void sendEvent(
        int eventType,
        Exception ex) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling sendEvent (2 args)");
        eventListener.sendEvent(eventType, ex, null);
    }

    /**
     * Sends a connection event to the application server.
     *
     * @param eventType the ConnectionEvent type
     * @param ex exception indicating a connection-related error
     * @param connectionHandle the connection handle associated with the
     * ManagedConnection instance
     */
    public void sendEvent(
        int eventType,
        Exception ex,
        Object connectionHandle) {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling sendEvent (3 args)");
        eventListener.sendEvent(eventType, ex, connectionHandle);
    }

    public boolean isTheSameStore(ConnectionRequestInfoImpl cxRequestInfo) {
        logger.log(
                Level.INFO,
                " X.- ({0}) Outbound:ManagedConnectionImpl::isTheSameStore called",
                myId);

        return store.isTheSameStore(cxRequestInfo);
    }

    /**
     * Physical connection *
     */
    private boolean openStore(ConnectionRequestInfo cxRequestInfo)
        throws ResourceException {
        logger.info("[Outbound:ManagedConnectionImpl] Calling openStore");

        ConnectionSpecImpl connectionInfo = new ConnectionSpecImpl();

        if (cxRequestInfo != null) {
            connectionInfo.setUserName(
                    ((ConnectionRequestInfoImpl) cxRequestInfo).getUserName());
            connectionInfo.setPassword(
                    ((ConnectionRequestInfoImpl) cxRequestInfo).getPassword());
            connectionInfo.setServerName(
                    ((ConnectionRequestInfoImpl) cxRequestInfo).getServerName());
            connectionInfo.setProtocol(
                    ((ConnectionRequestInfoImpl) cxRequestInfo).getProtocol());
        } else {
            // Use default values
            connectionInfo.setUserName(mcf.getUserName());
            connectionInfo.setPassword(mcf.getPassword());
            connectionInfo.setServerName(mcf.getServerName());
            connectionInfo.setProtocol(mcf.getProtocol());
        }

        try {
            store = new MailServerStore(connectionInfo);
        } catch (Exception e) {
            logger.log(
                    Level.SEVERE,
                    "Outbound:ManagedConnectionImpl::openStore threw exception: {0}",
                    e.toString());
            throw new ResourceException(e.getMessage());
        }

        return true;
    }

    private Folder getFolder(String folderName) throws ResourceException {
        logger.info("[Outbound:ManagedConnectionImpl] Calling getFolder");

        Folder folder;

        try {
            folder = store.getFolder(folderName);

            return folder;
        } catch (Exception e) {
            logger.log(
                    Level.SEVERE,
                    "Outbound:ManagedConnectionImpl::getFolder threw exception: {0}",
                    e);
        }

        return null;
    }

    /**
     * Application-specific method. Fetches new messages from the mail server.
     *
     * @return an array of messages
     */
    public Message[] getNewMessages(Folder folder) throws ResourceException {
        logger.info("[Outbound:ManagedConnectionImpl] Calling getNewMessages");

        try {
            return store.getNewMessages(folder);
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    "Outbound:ManagedConnectionImpl::getNewMessages threw exception: {0}",
                    e);
            throw new ResourceException(e.getMessage());
        }
    }

    /**
     * Application-specific method. Fetches new message headers from the mail
     * server.
     *
     * @return a String array of message headers
     */
    public String[] getNewMessageHeaders(Folder folder)
        throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionImpl] Calling getNewMessageHeaders");

        try {
            return store.getNewMessageHeaders(folder);
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    "Outbound:ManagedConnectionImpl::getNewMessageHeaders threw exception: {0}",
                    e);
            throw new ResourceException(e.getMessage());
        }
    }
}
