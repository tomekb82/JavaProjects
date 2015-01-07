/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionMetaData;


/**
 * This class provides information about an EIS instance connected through a
 * Connection instance.
 */
public class ConnectionMetaDataImpl implements ConnectionMetaData {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");
    private ManagedConnectionImpl mc;

    /**
     * Constructor.
     *
     * @param mc the physical connection of the JavaMailConnection that
     *           created this instance of ConnectionMetaDataImpl
     */
    public ConnectionMetaDataImpl(ManagedConnectionImpl mc) {
        logger.info("[Outbound:ConnectionMetaDataImpl::Constructor]");
        this.mc = mc;
    }

    /**
     * Returns product name of the underlying EIS instance connected through
     * the Connection that produced this metadata.
     *
     * @return product name of the EIS instance
     */
    @Override
    public String getEISProductName() throws ResourceException {
        String productName = "JavaMail Connector";

        return productName;
    }

    /**
     * Returns product version of the underlying EIS instance.
     *
     * @return product version of the EIS instance
     */
    @Override
    public String getEISProductVersion() throws ResourceException {
        String productVersion = "0.1";

        return productVersion;
    }

    /**
     * Returns the user name for an active connection known to the Mail
     * Server.
     *
     * @return String representing the user name
     */
    @Override
    public String getUserName() throws ResourceException {
        logger.info("[Outbound:ConnectionMetaDataImpl] Calling getUserName");

        if (mc.isDestroyed()) {
            throw new ResourceException(
                    resource.getString("DESTROYED_CONNECTION"));
        }

        return mc.getUserName();
    }

    // Could return other connection info (serverName, etc.)
}
