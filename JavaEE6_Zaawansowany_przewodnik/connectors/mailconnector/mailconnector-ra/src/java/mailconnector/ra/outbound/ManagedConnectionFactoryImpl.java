/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import mailconnector.api.JavaMailConnection;
import mailconnector.api.JavaMailConnectionFactory;


/**
 * An object of this class is a factory of both ManagedConnection and connection
 * factory instances. This class supports connection pooling by defining methods
 * for matching and creating connections. This class is implemented as a
 * JavaBeans component.
 */
@ConnectionDefinition(connectionFactory = JavaMailConnectionFactory.class, connectionFactoryImpl = JavaMailConnectionFactoryImpl.class, connection = JavaMailConnection.class, connectionImpl = JavaMailConnectionImpl.class)
public class ManagedConnectionFactoryImpl implements ManagedConnectionFactory,
    Serializable {
    private static final long serialVersionUID = -1632346849527329500L;
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    private transient PrintWriter out;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(
                this);

    // folderName property value
    @ConfigProperty(type = String.class, defaultValue = "UnknownFolderName")
    private String folderName = "Inbox";

    // password property value
    @ConfigProperty(type = String.class, defaultValue = "UnknownPassword")
    private String password = "unknownPassword";

    // Normally imap or pop3 
    private String protocol = "imap";

    //
    //	Properties
    //
    // serverName property value 
    private String serverName = "unknownServerName";

    // userName property value 
    private String userName = "unknownUserName";

    /**
     * Constructor.
     */
    public ManagedConnectionFactoryImpl() {
        logger.info(" 1.- Outbound:ManagedConnectionFactoryImpl::Constructor");
    }

    /**
     * Creates a Connection Factory instance. The ConnectionFactory instance is
     * initialized with the passed ConnectionManager. In the managed scenario,
     * ConnectionManager is provided by the application server.
     *
     * @param cxManager ConnectionManager to be associated with created EIS
     * connection factory instance
     *
     * @return EIS-specific Connection Factory instance
     *
     * @exception ResourceException if the attempt to create a connection
     * factory fails
     */
    @Override
    public Object createConnectionFactory(ConnectionManager cxManager)
        throws ResourceException {
        logger.info(
                " 2.- Outbound:ManagedConnectionFactoryImpl::createConnectionFactory(cxManager)");

        JavaMailConnectionFactoryImpl cf = null;

        try {
            cf = new JavaMailConnectionFactoryImpl(this, cxManager);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }

        return cf;
    }

    /**
     * Creates a Connection Factory instance. The Connection Factory instance is
     * initialized with a default ConnectionManager. In the non-managed
     * scenario, the ConnectionManager is provided by the resource adapter.
     *
     * @return EIS-specific Connection Factory instance
     *
     * @exception ResourceException if the attempt to create a connection
     * factory fails
     */
    @Override
    public Object createConnectionFactory() throws ResourceException {
        logger.info(
                " 2.- Outbound:ManagedConnectionFactoryImpl::createConnectionFactory()");

        return new JavaMailConnectionFactoryImpl(this, null);
    }

    /**
     * ManagedConnectionFactory uses the security information (passed as
     * Subject) and additional ConnectionRequestInfo (which is specific to
     * ResourceAdapter and opaque to application server) to create this new
     * connection.
     *
     * @param subject caller's security information
     * @param cxRequestInfo additional resource adapter specific connection
     * request information
     *
     * @return ManagedConnection instance
     *
     * @exception ResourceException if the attempt to create a connection fails
     */
    @Override
    public ManagedConnection createManagedConnection(
        Subject subject,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        logger.info(
                " 3A.- Outbound:ManagedConnectionFactoryImpl::createManagedConnection(Subject, cxM)");

        String name = null;

        ManagedConnectionImpl mc = new ManagedConnectionImpl(
                    this,
                    subject,
                    cxRequestInfo);

        return mc;
    }

    /**
     * Returns a matched managed connection from the candidate set of
     * connections. ManagedConnectionFactory uses the security info (as in
     * Subject) and information provided through ConnectionRequestInfo and
     * additional Resource Adapter specific criteria to do matching. A MC that
     * has the requested store is returned as a match
     *
     * @param connectionSet candidate connection set
     * @param subject caller's security information
     * @param cxRequestInfo additional resource adapter specific connection
     * request information
     *
     * @return ManagedConnection if resource adapter finds an acceptable match,
     * otherwise null
     *
     * @exception ResourceException if the match fails
     */
    @Override
    public ManagedConnection matchManagedConnections(
        Set connectionSet,
        Subject subject,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling matchManagedConnections");

        PasswordCredential pc = Util.getPasswordCredential(
                    this,
                    subject,
                    cxRequestInfo);

        String name = null;

        if (pc != null) {
            name = pc.getUserName();
        }

        Iterator it = connectionSet.iterator();

        while (it.hasNext()) {
            Object obj = it.next();

            if (obj instanceof ManagedConnectionImpl) {
                ManagedConnectionImpl mc = (ManagedConnectionImpl) obj;
                ManagedConnectionFactory mcf = mc.getManagedConnectionFactory();

                if (Util.isPasswordCredentialEqual(
                                mc.getPasswordCredential(),
                                pc)
                        && mc.isTheSameStore(
                                (ConnectionRequestInfoImpl) cxRequestInfo)) {
                    logger.log(
                            Level.INFO,
                            "Outbound:ManagedConnectionFactoryImpl::matchManagedConnections: found match mc = {0}",
                            mc);

                    return mc;
                }
            }
        }

        return null;
    }

    /**
     * Sets the log writer for this ManagedConnectionFactory instance. The log
     * writer is a character output stream to which all logging and tracing
     * messages for this ManagedConnectionfactory instance will be printed.
     *
     * @param out an output stream for error logging and tracing
     *
     * @exception ResourceException if the method fails
     */
    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    /**
     * Gets the log writer for this ManagedConnectionFactory instance.
     *
     * @return PrintWriter an output stream for error logging and tracing
     *
     * @exception ResourceException if the method fails
     */
    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return this.out;
    }

    /**
     * Returns the hash code for the ManagedConnectionFactory. (Concatenation of
     * the MCF property values)
     *
     * @return int hash code for the ManagedConnectionFactory
     */
    @Override
    public int hashCode() {
        //The rule here is that if two objects have the same Id
        //i.e. they are equal and the .equals method returns true
        //     then the .hashCode method *must* return the same
        //     hash code for those two objects
        int hashcode = "".hashCode();

        if (userName != null) {
            hashcode += userName.hashCode();
        }

        if (password != null) {
            hashcode += password.hashCode();
        }

        if (serverName != null) {
            hashcode += serverName.hashCode();
        }

        if (protocol != null) {
            hashcode += protocol.hashCode();
        }

        if (folderName != null) {
            folderName += folderName.hashCode();
        }

        return hashcode;
    }

    /**
     * Check if this ManagedConnectionFactory is equal to another
     * ManagedConnectionFactory.
     *
     * @param obj another ManagedConnectionFactory
     *
     * @return boolean true if the properties are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof ManagedConnectionFactoryImpl) {
                ManagedConnectionFactoryImpl other = (ManagedConnectionFactoryImpl) obj;

                if (!userName.equals(other.getUserName())) {
                    return false;
                }

                if (!password.equals(other.getPassword())) {
                    return false;
                }

                if (!serverName.equals(other.getServerName())) {
                    return false;
                }

                if (!folderName.equals(other.getFolderName())) {
                    return false;
                }

                if (!protocol.equals(other.getProtocol())) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Associate PropertyChangeListener with the ManagedConnectionFactory, in
     * order to notify about properties changes.
     *
     * @param lis the PropertyChangeListener to be associated with the
     * ManagedConnectionFactory
     */
    public void addPropertyChangeListener(PropertyChangeListener lis) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling addPropertyChangeListener");
        changes.addPropertyChangeListener(lis);
    }

    /**
     * Delete association of PropertyChangeListener with the
     * ManagedConnectionFactory.
     *
     * @param lis the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener lis) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling removePropertyChangeListener");
        changes.removePropertyChangeListener(lis);
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling readobject");
        logger.finest("Before readObject mcf");

        in.defaultReadObject();
        this.changes = new PropertyChangeSupport(this);
        this.out = null;
        logger.finest("After readObject mcf");
    }

    /**
     * Returns the value of the serverName property.
     *
     * @return the value of the serverName property
     */
    public String getServerName() {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling getServerName");

        return this.serverName;
    }

    /**
     * Sets the value of the serverName property
     *
     * @param serverName String containing the value to be assigned to
     * serverName
     */
    @ConfigProperty(type = String.class, defaultValue = "UnknownHostName")
    public void setServerName(String serverName) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling setServerName");

        String oldName = this.serverName;
        this.serverName = serverName;
        changes.firePropertyChange("serverName", oldName, serverName);
    }

    /**
     * Returns the value of the userName property.
     *
     * @return the value of the userName property
     */
    public String getUserName() {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling getUserName");

        return this.userName;
    }

    /**
     * Sets the value of the userName property.
     *
     * @param userName String containing the value to be assigned to userName
     */
    @ConfigProperty(type = String.class, defaultValue = "UnknownUserName")
    public void setUserName(String userName) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling setUserName");
        this.userName = userName;
    }

    /**
     * Returns the value of the password property.
     *
     * @return the value of the password property
     */
    public String getPassword() {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling getPassword");

        return this.password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param password String containing the value to be assigned to password
     */
    public void setPassword(String password) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling setPassword");
        this.password = password;
    }

    /**
     * Returns the value of the folderName property.
     *
     * @return the value of the folderName property
     */
    public String getFolderName() {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling getFolderName");

        return this.folderName;
    }

    /**
     * Sets the value of the folderName property.
     *
     * @param folderName String containing the value to be assigned to
     * folderName
     */
    public void setFolderName(String folderName) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling setFolderName");
        this.folderName = folderName;
    }

    /**
     * Returns the value of the protocol property.
     *
     * @return String the value of the protocol property
     */
    public String getProtocol() {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling getProtocol");

        return this.protocol;
    }

    /**
     * Sets the value of the protocol property.
     *
     * @param protocol String containing the value to be assigned to protocol
     */
    @ConfigProperty(description = "Typically Imap", type = String.class, defaultValue = "Imap")
    public void setProtocol(String protocol) {
        logger.info(
                "[Outbound:ManagedConnectionFactoryImpl] Calling setProtocol");
        this.protocol = protocol;
    }
}
