/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import mailconnector.share.ConnectionSpecImpl;


/**
 * @author Alejandro Murillo
 *
 */
public class MailServerStore {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    private Authenticator authenticator;
    private Properties mailProperties;
    private Session session;
    private Store store;
    private String password;
    private String protocol;
    private String serverName;
    private String userName;

    /**
     * Constructor.
     *
     * @param spec the ConnectionSpec (ConnectionRequestInfo)
     */
    public MailServerStore(ConnectionSpecImpl spec) throws Exception {
        logger.info("Outbound:MailServerStore::Constructor");
        userName = spec.getUserName();
        password = spec.getPassword();
        serverName = spec.getServerName();
        protocol = spec.getProtocol();

        this.authenticator = null;

        mailProperties = new Properties();
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.store.protocol", protocol);
        mailProperties.setProperty("mail.smtp.host", serverName);

        connectStore();
    }

    /**
     * Closes the Store.
     *
     * @exception Exception if the close fails
     */
    public void closeStore() throws Exception {
        /*
         * The JavaMail Session object does not have an explicit close.
         */
        logger.info("[Outbound:MailServerStore] Calling closeStore");
        this.store.close();
        this.store = null;
        this.authenticator = null;
        this.session = null;
    }

    /**
     * Opens a connection to the mail server. Associated with a MC
     *
     * @exception Exception if the open fails
     */
    private void connectStore() throws Exception {
        try {
            logger.info("[Outbound:MailServerStore] Calling connectStore");
            // Get a session object
            session = Session.getDefaultInstance(mailProperties);
            // Get a store object
            store = session.getStore();
            this.store.connect(serverName, userName, password);
        } catch (Exception te) {
            logger.log(
                    Level.SEVERE,
                    "[Outbound:MailServerStore] Caught an exception when obtaining a "
                    + "JavaMail Session: {0}",
                    te.toString());
            throw new Exception(te.getMessage());
        }
    }

    public Folder getFolder(String folderName) throws Exception {
        logger.info("[Outbound:MailServerStore] Calling getFolder");

        Folder folder;
        folder = this.store.getFolder(folderName);

        if ((folder == null) || (!folder.exists())) {
            Exception e = new Exception(
                        "[Outbound:MailServerStore] Folder " + folderName
                        + " does not exist or is not found");
            throw e;
        }

        return folder;
    }

    /**
     * Retrieves new messages. Used by a JavaMailConnection
     *
     * @return an array of messages
     */
    public Message[] getNewMessages(Folder folder) throws Exception {
        logger.info("[Outbound:MailServerStore] Calling getNewMessages");

        if ((folder == null) || (!folder.exists())) {
            Exception e = new Exception(
                        "[Outbound:MailServerStore] Folder " + folder
                        + " does not exist or is not found");
            throw e;
        }

        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }

        //
        // Deliver only new messages to the MDB
        //
        try {
            int newMsgs = folder.getNewMessageCount();

            if (newMsgs > 0) {
                int msgCount = folder.getMessageCount();
                Message[] msgs = // changed because mock-javamail seems to count from
                    // 0, not 1
                    // folder.getMessages(msgCount - newMsgs + 1, msgCount);
                    folder.getMessages(msgCount - newMsgs, msgCount - 1);

                return msgs;
            }
        } catch (Exception e) {
            logger.severe(
                    "[Outbound:MailServerStore] Exception obtaining messages from mail server");
        }

        return null;
    }

    /**
     * Retrieves headers of new messages.
     *
     * @return a string array containing the message headers
     */
    public String[] getNewMessageHeaders(Folder folder)
        throws Exception {
        logger.info("[Outbound:MailServerStore] Calling getNewMessageHeaders");

        if ((folder == null) || (!folder.exists())) {
            Exception e = new Exception(
                        "[Outbound:MailServerStore] Folder " + folder
                        + " does not exist or is not found");
            throw e;
        }

        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }

        //
        // Deliver only new messages to the MDB
        //
        try {
            int newMsgs = folder.getNewMessageCount();

            if (newMsgs > 0) {
                int msgCount = folder.getMessageCount();
                Message[] msgs = folder.getMessages(
                            msgCount - newMsgs + 1,
                            msgCount);
                String[] headers = new String[msgs.length];
                logger.log(
                        Level.FINE,
                        "[Outbound:MailServerStore] messages length: {0}",
                        msgs.length);
                logger.log(
                        Level.FINE,
                        "[Outbound:MailServerStore] headers length: {0}",
                        headers.length);

                for (int i = 0; i < headers.length; i++) {
                    logger.log(
                            Level.FINE,
                            "[Outbound:MailServerStore] Packing message with SUBJECT: {0}",
                            msgs[i].getSubject());
                    headers[i] = msgs[i].getSubject();
                }

                return headers;
            }
        } catch (Exception e) {
            logger.severe(
                    "[Outbound:MailServerStore] Exception obtaining message headers from mail server:");
        }

        return null;
    }

    public boolean isTheSameStore(ConnectionRequestInfoImpl cxRequestInfo) {
        logger.info("[Outbound:MailServerStore] Calling isTheSameStore");

        if (!userName.equals(cxRequestInfo.getUserName())) {
            return false;
        }

        if (!password.equals(cxRequestInfo.getPassword())) {
            return false;
        }

        if (!serverName.equals(cxRequestInfo.getServerName())) {
            return false;
        }

        if (!protocol.equals(cxRequestInfo.getProtocol())) {
            return false;
        }

        logger.info("[Outbound:MailServerStore] isTheSameStore: found match!");

        return true;
    }
}
