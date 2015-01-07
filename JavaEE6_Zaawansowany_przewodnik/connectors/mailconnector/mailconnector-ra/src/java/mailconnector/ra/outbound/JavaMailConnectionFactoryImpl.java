/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.naming.Reference;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionSpec;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ManagedConnectionFactory;
import mailconnector.api.JavaMailConnection;
import mailconnector.api.JavaMailConnectionFactory;
import mailconnector.share.ConnectionSpecImpl;


/**
 * This implementation class provides an interface for getting a connection to a
 * Mail Server. It looks up a ConnectionFactory instance in the JNDI namespace
 * and uses it to get Mail Server connections.
 */
public class JavaMailConnectionFactoryImpl implements JavaMailConnectionFactory,
    Serializable, Referenceable {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    private static final long serialVersionUID = 4121786082353298551L;
    private transient PrintWriter out;
    private transient int milliseconds;
    private ConnectionManager cm;
    private ManagedConnectionFactory mcf;

    // Local variables
    private Reference reference;

    /**
     * JavaMailConnectionFactoryImpl constructor (no arguments).
     */
    public JavaMailConnectionFactoryImpl() {
    }

    /**
     * JavaMailConnectionFactoryImpl constructor.
     *
     * @param mcf the ManagedConnectionFactory that created this
     * JavaMailConnectionFactory instance
     * @param cm the ConnectionManager
     */
    public JavaMailConnectionFactoryImpl(
        ManagedConnectionFactory mcf,
        ConnectionManager cm) {
        logger.info(" 3.- Outbound:JavaMailConnectionFactoryImpl::Constructor");
        this.mcf = mcf;

        if (cm == null) {
            this.cm = new ConnectionManagerImpl();
        } else {
            this.cm = cm;
        }
    }

    /**
     * Gets a connection to the Mail Server. Passes along mail server and user
     * info.
     *
     * @return Connection        Connection instance
     */
    @Override
    public JavaMailConnection createConnection() throws ResourceException {
        logger.info(
                " 3.- Outbound:JavaMailConnectionFactoryImpl::createConnection -- Client requested a "
                + "connection. Get it from Connection Manager");

        // Use the default values of the managed connection factory
        JavaMailConnection con = (JavaMailConnection) cm.allocateConnection(
                    mcf,
                    null);

        logger.info(
                " 6.- Outbound:JavaMailConnectionFactoryImpl::createConnection -- Returning Connection to Client");

        return con;
    }

    /**
     * Gets a connection to a Mail Server instance. A component should use the
     * getConnection variant with a javax.resource.cci.ConnectionSpec parameter
     * if it needs to pass any resource-adapter-specific security information
     * and connection parameters.
     *
     * @param properties connection parameters and security information
     * specified as ConnectionSpec instance
     * @return a JavaMailConnection instance
     */
    @Override
    public JavaMailConnection createConnection(ConnectionSpec properties)
        throws ResourceException {
        JavaMailConnection con = null;

        logger.info(
                " 3.- Outbound:JavaMailConnectionFactoryImpl::createConnection -- Client requested a "
                + "connection. Get it from Connection Manager");

        ConnectionRequestInfoImpl info = new ConnectionRequestInfoImpl(
                    ((ConnectionSpecImpl) properties).getUserName(),
                    ((ConnectionSpecImpl) properties).getPassword(),
                    ((ConnectionSpecImpl) properties).getFolderName(),
                    ((ConnectionSpecImpl) properties).getServerName(),
                    ((ConnectionSpecImpl) properties).getProtocol());

        con = (JavaMailConnection) cm.allocateConnection(mcf, info);

        logger.info(
                " 6.- Outbound:JavaMailConnectionFactoryImpl::createConnection -- Returning Connection to user");

        return con;
    }

    /**
     * Sets the log writer for the ConnectionFactory instance. The log writer is
     * a character output stream to which all logging and tracing messages for
     * the ConnectionFactory instance will be printed.
     *
     * @param out log writer associated with the ConnectionFactory
     */
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    /**
     * Gets the log writer for the ConnectionFactory instance.
     *
     * @return PrintWriter log writer associated with the ConnectionFactory
     */
    public PrintWriter getLogWriter() throws ResourceException {
        return out;
    }

    /**
     * Sets the maximum time in milliseconds that this connection factory will
     * wait while attempting to connect to a Mail Server. A value of zero
     * specifies that the timeout is the default system timeout if there is one;
     * otherwise it specifies that there is no timeout. When a ConnectionFactory
     * object is created, the timeout is initially zero.
     *
     * @param milliseconds connection establishment timeout in milliseconds
     */
    public void setTimeout(int milliseconds) throws ResourceException {
        this.milliseconds = milliseconds;
    }

    /**
     * Gets the maximum time in milliseconds that this connection factory can
     * wait while attempting to connect to a Mail Server.
     *
     * @return connection establishment timeout in milliseconds
     */
    public int getTimeout() throws ResourceException {
        return milliseconds;
    }

    /**
     * This method is declared in the javax.resource.Referenceable interface and
     * should be implemented in order to support JNDI registration.
     *
     * @param reference a Reference instance
     */
    @Override
    public void setReference(Reference reference) {
        logger.info(
                "[Outbound:JavaMailConnectionFactoryImpl] Calling setReference");
        this.reference = reference;
    }

    /**
     * This method is declared in the javax.naming.Referenceable interface and
     * should be implemented in order to support JNDI registration.
     *
     * @return a Reference instance
     */
    @Override
    public Reference getReference() {
        logger.info(
                "[Outbound:JavaMailConnectionFactoryImpl] Calling getReference");

        return reference;
    }
}
