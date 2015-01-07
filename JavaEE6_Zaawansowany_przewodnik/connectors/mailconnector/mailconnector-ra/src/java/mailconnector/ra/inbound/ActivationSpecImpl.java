/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.*;
import mailconnector.api.JavaMailMessageListener;


/**
 * This class implements the Activation Spec class of the Sample mailconnector
 * connector.
 *
 * @author Alejandro Murillo
 *
 */
@Activation(messageListeners =  {
    JavaMailMessageListener.class}
)
public class ActivationSpecImpl implements ActivationSpec, Serializable {
    private static final long serialVersionUID = -7473296304641786510L;
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    private ResourceAdapter resourceAdapter = null;

    // folderName property value
    @ConfigProperty()
    private String folderName = "INBOX";

    // Polling interval (seconds)
    private String interval = "30";

    // password property value
    @ConfigProperty()
    private String password = "";

    // protocol property value
    @ConfigProperty(description = "Normally imap or pop3")
    private String protocol = "imap";

    // serverName property value
    @ConfigProperty()
    private String serverName = "";

    // userName property value
    @ConfigProperty()
    private String userName = "";

    /**
     * Constructor. Creates a new instance of the base activation spec.
     */
    public ActivationSpecImpl() {
    }

    /**
     * Returns the value of the serverName property.
     *
     * @return String containing the value of the serverName property
     */
    public String getServerName() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getServerName");

        return this.serverName;
    }

    /**
     * Sets the value of the serverName property.
     *
     * @param serverName String containing the value to be assigned to
     * serverName
     */
    public void setServerName(String serverName) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setServerName");
        this.serverName = serverName;
    }

    /**
     * Returns the value of the userName property.
     *
     * @return String containing the value of the userName property
     */
    public String getUserName() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getUserName");

        return this.userName;
    }

    /**
     * Sets the value of the userName property.
     *
     * @param userName String containing the value to be assigned to userName
     */
    public void setUserName(String userName) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setUserName");
        this.userName = userName;
    }

    /**
     * Returns the value of the password property.
     *
     * @return String containing the value of the password property
     */
    public String getPassword() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getPassword");

        return this.password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param password String containing the value to be assigned to password
     */
    public void setPassword(String password) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setPassword");
        this.password = password;
    }

    /**
     * Returns the value of the folderName property.
     *
     * @return String containing the value of the folderName property
     */
    public String getFolderName() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getFolderName");

        return this.folderName;
    }

    /**
     * Sets the value of the folderName property.
     *
     * @param folderName String containing the value to be assigned to
     * folderName
     */
    public void setFolderName(String folderName) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setFolderName");
        this.folderName = folderName;
    }

    /**
     * Returns the value of the protocol property.
     *
     * @return String containing the value of the protocol property
     */
    public String getProtocol() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getProtocol");

        return this.protocol;
    }

    /**
     * Sets the value of the protocol property.
     *
     * @param protocol String containing the value to be assigned to protocol
     */
    public void setProtocol(String protocol) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setProtocol");
        this.protocol = protocol;
    }

    /**
     * Returns the value of the interval property.
     *
     * @return String containing the value of the interval property
     */
    public String getInterval() {
        logger.info("[Inbound:ActivationSpecImpl] Calling getInterval");

        return this.interval;
    }

    /**
     * Sets the value of the interval property.
     *
     * @param interval String containing the value to be assigned to interval
     */
    public void setInterval(String interval) {
        logger.info("[Inbound:ActivationSpecImpl] Calling setInterval");
        this.interval = interval;
    }

    /**
     * Validates the configuration properties. TBD: verify that a connection to
     * the mail server can be done
     *
     * @exception InvalidPropertyException
     */
    @Override
    public void validate() throws InvalidPropertyException {
    }

    /**
     * Sets the resource adapter.
     *
     * @param ra the resource adapter
     */
    @Override
    public void setResourceAdapter(ResourceAdapter ra)
        throws ResourceException {
        logger.info("[Inbound:ActivationSpecImpl] Calling setResourceAdapter");
        this.resourceAdapter = ra;
    }

    /**
     * Gets the resource adapter.
     *
     * @return the resource adapter
     */
    @Override
    public ResourceAdapter getResourceAdapter() {
        logger.info("[ActivationSpecImpl] Calling getResourceAdapter");

        return resourceAdapter;
    }
}
