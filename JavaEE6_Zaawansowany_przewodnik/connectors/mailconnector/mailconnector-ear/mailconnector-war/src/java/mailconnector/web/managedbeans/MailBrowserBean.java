/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.web.managedbeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import mailconnector.api.JavaMailConnection;
import mailconnector.api.JavaMailConnectionFactory;
import mailconnector.share.ConnectionSpecImpl;


@Named
@SessionScoped
public class MailBrowserBean implements Serializable {
    static final Logger logger = Logger.getLogger(
                "mailconnector.web.managedbeans");
    private static final long serialVersionUID = -6878032840425047855L;
    protected String folder = "Inbox";
    protected String mailBox = "";
    protected String protocol = "IMAP";
    protected String server = "localhost";
    protected String user = "";
    protected String username = "";
    protected boolean connectionAvailable;
    protected int numberOfMessages = 0;
    JavaMailConnection connection = null;
    @Resource(name = "eis/JavaMailConnectionFactory")
    JavaMailConnectionFactory connectionFactory;
    MailMessage[] mailMessages;
    Message[] messages = null;

    /** Creates a new instance of MailBrowserBean */
    public MailBrowserBean() {
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        username = getUser();

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = username + "-" + folder + "@" + server;
    }

    public int getNumberOfMessages() {
        return numberOfMessages;
    }

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    /**
     * Get the value of connectionAvailable
     *
     * @return the value of connectionAvailable
     */
    public boolean isConnectionAvailable() {
        if (connection == null) {
            setConnectionAvailable(false);
        } else {
            setConnectionAvailable(true);
        }

        return connectionAvailable;
    }

    /**
     * Set the value of connectionAvailable
     *
     * @param connectionAvailable new value of connectionAvailable
     */
    public void setConnectionAvailable(boolean connectionAvailable) {
        this.connectionAvailable = connectionAvailable;
    }

    public JavaMailConnection getConnection() {
        logger.info("Entering MailBrowserBean.getConnection");

        try {
            ConnectionSpecImpl connectionSpec = new ConnectionSpecImpl() {
                };

            connectionSpec.setUserName(username);
            connectionSpec.setPassword(username);
            connectionSpec.setServerName(server);
            connectionSpec.setFolderName(folder);
            connectionSpec.setProtocol(protocol);

            this.mailBox = username + "-" + folder + "@" + server;

            if (connectionFactory != null) {
                connection = connectionFactory.createConnection(connectionSpec);
            } else {
                logger.info(
                        "MailBrowserBean.getConnection: "
                        + "connectionFactory is null");
            }

            if (connection != null) {
                setConnectionAvailable(true);
            } else {
                setConnectionAvailable(false);
            }
        } catch (Exception e) {
            logger.log(
                    Level.SEVERE,
                    "MailBrowserBean.getConnection: {0}",
                    e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void setConnection(JavaMailConnection connection) {
        this.connection = connection;
    }

    public MailMessage[] getMailMessages() {
        return mailMessages;
    }

    public void setMailMessages(MailMessage[] mailMessages) {
        this.mailMessages = mailMessages;
    }

    public String getUser() {
        String principalName = "";

        try {
            ExternalContext context = FacesContext.getCurrentInstance()
                                                  .getExternalContext();
            Principal principal = context.getUserPrincipal();
            principalName = principal.getName();
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                " getUser: {0}",
                e.getMessage());
        }

        return principalName;
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = null;

        try {
            ExternalContext context = FacesContext.getCurrentInstance()
                                                  .getExternalContext();
            request = (HttpServletRequest) context.getRequest();
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                " getRequest: {0}",
                e.getMessage());
        }

        return request;
    }

    public String retrieveMessages() {
        logger.info("Entering MailBrowserBean.retrieveMessages");

        try {
            connection = getConnection();

            if (connectionAvailable) {
                messages = connection.getNewMessages();

                if (messages != null) {
                    setNumberOfMessages(messages.length);

                    mailMessages = new MailMessage[messages.length];

                    for (short i = 0; i < messages.length; i++) {
                        MailMessage newMessage = new MailMessage();

                        newMessage.setId(i);

                        InternetAddress[] from = (InternetAddress[]) messages[i]
                            .getFrom();
                        String sender = "Unknown";

                        if (from.length > 0) {
                            sender = from[0].toString();
                        }

                        newMessage.setSender(sender);

                        Address[] recipients = (Address[]) messages[i]
                            .getAllRecipients();
                        String recipient = "Unknown";

                        if (recipients.length > 0) {
                            recipient = recipients[0].toString();
                        }

                        newMessage.setRecipient(recipient);

                        String subject = messages[i].getSubject();
                        newMessage.setSubject(subject);

                        String body = (String) messages[i].getContent();
                        newMessage.setBody(body);
                        mailMessages[i] = newMessage;
                    }
                } else {
                    setNumberOfMessages(0);
                }
            } else {
                setConnectionAvailable(false);
            }
        } catch (Exception e) {
            logger.log(
                    Level.SEVERE,
                    "MailBrowserBean.retrieveMessages: {0}",
                    e.toString());
        }

        return ("mailfolder");
    }

    public String logout() {
        logger.info("Entering MailBrowserBean.logout");

        try {
            HttpServletRequest request = getRequest();
            request.logout();
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                "logout failed: {0}",
                e.toString());
        }

        return "login";
    }
}
