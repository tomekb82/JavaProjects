/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;


/**
 * The default ConnectionManager implementation for the non-managed scenario.
 * This provides a hook for a resource adapter to pass a connection
 * request to an application server.
 */
public class ConnectionManagerImpl implements ConnectionManager, Serializable {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    private static final long serialVersionUID = -6442502821729146281L;

    public ConnectionManagerImpl() {
        logger.info("[Outbound:ConnectionManagerImpl::Constructor]");
    }

    /**
     * The method allocateConnection is called by the resource adapter's
     * connection factory instance. This lets the connection factory instance
     * provided by the resource adapter pass a connection request to the
     * ConnectionManager instance. The connectionRequestInfo parameter
     * represents information specific to the resource adapter for handling
     * the connection request.
     *
     * @param mcf        used by application server to delegate connection
     *                  matching/creation
     * @param cxRequestInfo  connection request information
     *
     * @return  connection handle with an EIS specific connection interface
     *
     * @exception ResourceException if an error occurs
     */
    @Override
    public Object allocateConnection(
        ManagedConnectionFactory mcf,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        logger.info(
                "[Outbound:ConnectionManagerImpl] Calling allocateConnection");

        ManagedConnection mc = mcf.createManagedConnection(null, cxRequestInfo);

        return mc.getConnection(null, cxRequestInfo);
    }
}
