/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.share;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;
import javax.resource.cci.ConnectionSpec;


/**
 * This implementation class is used by an application component to pass
 * connection-specific info/properties to the getConnection method in the
 * JavaMailConnectionFactoryImpl class. This class is implemented as a JavaBeans
 * component.
 */
public class ConnectionSpecImpl implements ConnectionSpec {
    static final Logger logger = Logger.getLogger("mailconnector.share");
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String folderName = null;
    private String password = null;
    private String protocol = null;
    private String serverName = null;
    private String userName = null;

    /**
     * ConnectionSpecImpl constructor (no arguments).
     */
    public ConnectionSpecImpl() {
    }

    /**
     * Returns the user name value.
     *
     * @return String containing the user name
     */
    public String getUserName() {
        logger.info("[Share:ConnectionSpecImpl] Calling getUserName");

        return this.userName;
    }

    /**
     * Returns the password value.
     *
     * @return String containing the password
     */
    public String getPassword() {
        logger.info("[Share:ConnectionSpecImpl] Calling getPassword");

        return this.password;
    }

    /**
     * Returns the server name value.
     *
     * @return String containing the server name
     */
    public String getServerName() {
        logger.info("[Share:ConnectionSpecImpl] Calling getServerName");

        return this.serverName;
    }

    /**
     * Returns the folder name value.
     *
     * @return String containing the folder name
     */
    public String getFolderName() {
        logger.info("[Share:ConnectionSpecImpl] Calling getFolderName");

        return this.folderName;
    }

    /**
     * Returns the protocol value.
     *
     * @return String containing the protocol
     */
    public String getProtocol() {
        logger.info("[Share:ConnectionSpecImpl] Calling getProtocol");

        return this.protocol;
    }

    /**
     * Sets the user name value.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        logger.info("[Share:ConnectionSpecImpl] Calling setUserName");

        String oldName = this.userName;
        this.userName = userName;
        changes.firePropertyChange("userName", oldName, userName);
    }

    /**
     * Sets the password value.
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        logger.info("[Share:ConnectionSpecImpl] Calling setPassword");

        String oldPass = this.password;
        this.password = password;
        changes.firePropertyChange("password", oldPass, password);
    }

    /**
     * Sets the folder name value.
     *
     * @param folderName the folder name
     */
    public void setFolderName(String folderName) {
        logger.info("[Share:ConnectionSpecImpl] Calling setFolderName");

        String oldFolderName = this.folderName;
        this.folderName = folderName;
        changes.firePropertyChange("folderName", oldFolderName, folderName);
    }

    /**
     * Sets the server name value.
     *
     * @param serverName the server name
     */
    public void setServerName(String serverName) {
        logger.info("[Share:ConnectionSpecImpl] Calling setServerName");

        String oldServerName = this.serverName;
        this.serverName = serverName;
        changes.firePropertyChange("serverName", oldServerName, serverName);
    }

    /**
     * Sets the protocol value.
     *
     * @param protocol the server name
     */
    public void setProtocol(String protocol) {
        logger.info("[Share:ConnectionSpecImpl] Calling setProtocol");

        String oldProtocol = this.protocol;
        this.protocol = protocol;
        changes.firePropertyChange("protocol", oldProtocol, protocol);
    }

    /**
     * Associate PropertyChangeListener with the ConnectionSpecImpl in order to
     * notify about properties changes.
     *
     * @param listener the listener to be associated with the connection spec
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        logger.info(
                "[Share:ConnectionSpecImpl] Calling addPropertyChangeListener");
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Delete association of PropertyChangeListener with the ConnectionSpecImpl.
     *
     * @param listener the listener to be deleted
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        logger.info(
                "[Share:ConnectionSpecImpl] Calling removePropertyChangeListener");
        changes.removePropertyChangeListener(listener);
    }
}
