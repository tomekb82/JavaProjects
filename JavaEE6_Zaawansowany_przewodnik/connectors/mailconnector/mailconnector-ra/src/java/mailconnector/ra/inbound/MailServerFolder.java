/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;


/**
 * @author Alejandro Murillo
 *
 */
public class MailServerFolder {
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    private Authenticator authenticator;
    private Folder folder;
    private Properties mailProperties;
    private Session session;
    private Store store;
    private String folderName;
    private String password;
    private String protocol;
    private String serverName;
    private String userName;

    /**
     * Constructor.
     *
     * @param spec the ActivationSpec for the MDB
     */
    public MailServerFolder(ActivationSpecImpl spec) throws Exception {
        logger.info("[Inbound:MailServerFolder] Constructor");
        folderName = spec.getFolderName();
        userName = spec.getUserName();
        password = spec.getPassword();
        serverName = spec.getServerName();
        protocol = spec.getProtocol();

        this.authenticator = null;

        mailProperties = new Properties();
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.store.protocol", protocol);
        mailProperties.setProperty("mail.smtp.host", serverName);

        try {
            open();
        } catch (Exception te) {
            logger.warning(
                    "[Inbound:MailServerFolder::Constructor] Caught an exception when opening the folder");
            throw te;
        }
    }

    /**
     * Closes the folder.
     *
     * @exception Exception if the close fails
     */
    public void close() throws Exception {
        /*
         * The JavaMail Session object does not have an explicit close.
         */
        logger.info("[Inbound:MailServerFolder] Calling close");
        this.store.close();
        this.store = null;
        this.session = null;
        this.authenticator = null;
    }

    /**
     * Opens a connection to the mail server.
     *
     * @exception Exception  if the open fails
     */
    private void open() throws Exception {
        logger.info("[Inbound:MailServerFolder] Calling open");

        try {
            // Get a session object
            session = Session.getDefaultInstance(mailProperties);
            // Get a store object
            store = session.getStore();
        } catch (Exception te) {
            logger.info(
                    "[Inbound:MailServerFolder] Caught an exception when obtaining a "
                    + "JavaMail Session");
            throw te;
        }

        store.connect(serverName, userName, password);
        folder = store.getFolder(folderName);

        if ((folder == null) || (!this.folder.exists())) {
            Exception e = new Exception(
                        "Folder " + folderName
                        + " does not exist or is not found");
            throw e;
        }

        folder.open(Folder.READ_WRITE);
    }

    private void reopen() throws Exception {
        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }
    }

    /**
     * Retrieves new messages.
     *
     * @return an array of messages
     */
    public Message[] getNewMessages() throws Exception {
        logger.info("[Inbound:MailServerFolder] Calling getNewMessages");
        reopen(); //Make sure the folder is open

        //
        // Deliver only new messages to the MDB
        //
        try {
            if (folder.hasNewMessages()) {
                int newMsgs = folder.getNewMessageCount();
                int msgCount = folder.getMessageCount();
                Message[] msgs = folder.getMessages(
                            msgCount - newMsgs + 1,
                            msgCount);

                return msgs;
            }
        } catch (Exception e) {
            logger.info(
                    "[Inbound:MailServerFolder] Exception obtaining messages from mail server");
        }

        return null;
    }

    /**
     * Retrieves headers of new messages.
     *
     * @return a string array containing the message headers
     */
    public String[] getNewMessageHeaders() throws Exception {
        logger.info("[Inbound:MailServerFolder] Calling getNewMessageHeaders");
        reopen(); //Make sure the folder is open

        //
        // Deliver only new messages to the MDB
        //
        try {
            if (folder.hasNewMessages()) {
                int newMsgs = folder.getNewMessageCount();
                int msgCount = folder.getMessageCount();
                Message[] msgs = folder.getMessages(
                            msgCount - newMsgs + 1,
                            msgCount);
                String[] headers = new String[msgs.length];
                logger.log(Level.INFO, "messages length: {0}", msgs.length);
                logger.log(Level.INFO, "headers length: {0}", headers.length);

                for (int i = 0; i < headers.length; i++) {
                    logger.log(
                            Level.INFO,
                            "[Inbound:MailServerFolder] Packing message with SUBJECT: {0}",
                            msgs[i].getSubject());
                    headers[i] = msgs[i].getSubject();
                }

                return headers;
            }
        } catch (Exception e) {
            logger.severe(
                    "[Inbound:MailServerFolder] Exception obtaining messages from mail server:");
        }

        return null;
    }

    public boolean hasNewMessages() throws Exception {
        logger.info("[Inbound:MailServerFolder] Calling hasNewMessages");
        reopen(); //Make sure the folder is open

        /****
           logger.info("Folder <" + folder.getName() + "> has ("
           + folder.getUnreadMessageCount() +  ") unread messages");
           logger.info("Folder <" + folder.getName() + "> has ("
           + folder.getNewMessageCount() +  ") NEW messages");
         ****/
        return folder.hasNewMessages();
    }
}
