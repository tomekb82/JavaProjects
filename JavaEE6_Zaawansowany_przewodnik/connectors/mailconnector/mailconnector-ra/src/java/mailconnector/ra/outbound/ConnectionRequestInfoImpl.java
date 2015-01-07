/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.logging.Logger;
import javax.resource.spi.ConnectionRequestInfo;


/**
 * This class implements the ConnectionRequestInfo interface, which enables a
 * resource adapter to pass its own request-specific data structure across the
 * connection request flow.
 */
public class ConnectionRequestInfoImpl implements ConnectionRequestInfo {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    private String folderName = null;
    private String password = null;
    private String protocol = null;
    private String serverName = null;
    private String userName = null;

    /**
     * Constructor.
     *
     * @param userName  user name
     * @param password  user password
     * @param folderName  folder name
     * @param serverName  server name
     * @param protocol  protocol
     */
    public ConnectionRequestInfoImpl(
        String userName,
        String password,
        String folderName,
        String serverName,
        String protocol) {
        this.userName = userName;
        this.password = password;
        this.folderName = folderName;
        this.serverName = serverName;
        this.protocol = protocol;
        logger.info("[Outbound:ConnectionRequestInfoImpl::Constructor]");
    }

    //
    //	Getter methods
    //
    /**
     * Returns the user name value.
     *
     * @return   String containing the user name
     */
    public String getUserName() {
        logger.info(
                "[Outbound:ConnectionRequestInfoImpl]: Calling getUserName");

        return userName;
    }

    /**
     * Returns the password value.
     *
     * @return   String containing the password
     */
    public String getPassword() {
        logger.info(
                "[Outbound:ConnectionRequestInfoImpl]: Calling getPassword");

        return password;
    }

    /**
     * Returns the server name value.
     *
     * @return   String containing the server name
     */
    public String getServerName() {
        logger.info(
                "[Outbound:ConnectionRequestInfoImpl]: Calling getServerName");

        return serverName;
    }

    /**
     * Returns the folder name value.
     *
     * @return   String containing the folder name
     */
    public String getFolderName() {
        logger.info(
                "[Outbound:ConnectionRequestInfoImpl]: Calling getFolderName");

        return folderName;
    }

    /**
     * Returns the protocol value.
     *
     * @return   String containing the protocol
     */
    public String getProtocol() {
        logger.info(
                "[Outbound:ConnectionRequestInfoImpl]: Calling getProtocol");

        return protocol;
    }

    /**
     * Checks whether this instance is equal to another.
     *
     * @param obj  other object
     *
     * @return  true if the two instances are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj != null) {
            if (obj instanceof ConnectionRequestInfoImpl) {
                ConnectionRequestInfoImpl other = (ConnectionRequestInfoImpl) obj;

                equal = (this.userName).equals(other.userName)
                    && (this.password).equals(other.password)
                    && (this.serverName).equals(other.serverName)
                    && (this.protocol).equals(other.protocol);
            }
        }

        return equal;
    }

    /**
     * Returns the hashCode of the ConnectionRequestInfoImpl.
     *
     * @return  the hash code of this instance
     */
    @Override
    public int hashCode() {
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

        return hashcode;
    }
}
