/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.api;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionSpec;


/**
 * Interface for obtaining mail server connections.
 *
 * @author Alejandro E. Murillo
 */
public interface JavaMailConnectionFactory {
    /**
     * Gets a connection to the Mail Server.
     * Passes along mail server and user info.
     *
     * @return Connection        Connection instance
     */
    public JavaMailConnection createConnection() throws ResourceException;

    /**
     * Gets a connection to a Mail Server instance. A component should use
     * the getConnection variant with a javax.resource.cci.ConnectionSpec
     * parameter if it needs to pass any resource-adapter-specific security
     * information and connection parameters.
     *
     * @param properties  connection parameters and security information
     *                    specified as ConnectionSpec instance
     * @return  a JavaMailConnection instance
     */
    public JavaMailConnection createConnection(ConnectionSpec properties)
        throws ResourceException;
}
