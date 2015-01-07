/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.logging.Logger;
import java.util.ResourceBundle;
import javax.resource.ResourceException;
import javax.resource.spi.IllegalStateException;
import javax.resource.spi.ManagedConnectionMetaData;


/**
 * The ManagedConnectionMetaData interface provides information about the
 * underlying EIS instance associated with a ManagedConnection instance. An
 * application server uses this information to get runtime information about
 * a connected EIS instance.
 */
public class ManagedConnectionMetaDataImpl implements ManagedConnectionMetaData {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private ManagedConnectionImpl mc;

    /**
     * Constructor.
     *
     * @param mc  the managed connection that created this instance of
     *            ManagedConnectionMetaData
     */
    public ManagedConnectionMetaDataImpl(ManagedConnectionImpl mc) {
        logger.info("Outbound:ManagedConnectionMetaDataImpl::Constructor");
        this.mc = mc;
    }

    /**
     * Returns the product name of the underlying EIS instance connected
     * through the ManagedConnection.
     *
     * @return  product name of the EIS instance
     */
    @Override
    public String getEISProductName() throws ResourceException {
        String productName = null;

        // ToDo: Add service specific code here
        return productName;
    }

    /**
     * Returns the product version of the underlying EIS instance connected
     * through the ManagedConnection.
     *
     * @return  product version of the EIS instance
     */
    @Override
    public String getEISProductVersion() throws ResourceException {
        String productVersion = null;

        // ToDo: Add service specific code here
        return productVersion;
    }

    /**
     * Returns the maximum number of active concurrent connections that
     * an EIS instance can support across client processes. If an EIS
     * instance does not know about (or does not have) any such limit, it
     * returns zero.
     *
     * @return  maximum number of active concurrent connections
     */
    @Override
    public int getMaxConnections() throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionMetaDataImpl] Calling getMaxConnections");

        int maxConnections = 0;

        // ToDo: Add service specific code here
        return maxConnections;
    }

    /**
     * Returns the name of the user associated with the ManagedConnection
     * instance. The name corresponds to the resource principal under whose
     * security context a connection to the EIS instance has been established.
     *
     * @return  name of the user
     */
    @Override
    public String getUserName() throws ResourceException {
        logger.info(
                "[Outbound:ManagedConnectionMetaDataImpl] Calling getUserName");

        if (mc.isDestroyed()) {
            throw new IllegalStateException(
                    resource.getString("DESTROYED_CONNECTION"));
        }

        return mc.getUserName();
    }
}
