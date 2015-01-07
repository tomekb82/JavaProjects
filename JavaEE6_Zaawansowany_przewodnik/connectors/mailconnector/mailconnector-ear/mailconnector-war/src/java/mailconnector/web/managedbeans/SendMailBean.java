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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


@Named
@SessionScoped
public class SendMailBean implements Serializable {
    static final Logger logger = Logger.getLogger(
                "mailconnector.web.managedbeans.SendMailBean");
    private static final long serialVersionUID = -4310022548431556220L;
    @Resource(name = "mail/MySession")
    protected Session session;
    protected String body;
    protected String recipient = "user1";
    protected String sender = "";
    protected String server = "localhost";
    protected String subject = "What's Up?";
    protected String user = "";
    protected boolean sendSuccessful = true;
    Principal principal = null;

    /** Creates a new instance of SendMailBean */
    public SendMailBean() {
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    // sender is the user (UserPrincipal)
    public String getSender() {
        sender = getUser();

        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSendSuccessful() {
        return sendSuccessful;
    }

    public void setSendSuccessful(boolean sendSuccessful) {
        this.sendSuccessful = sendSuccessful;
    }

    public String getUser() {
        String principalName = "";

        try {
            ExternalContext context = FacesContext.getCurrentInstance()
                                                  .getExternalContext();
            principal = context.getUserPrincipal();
            principalName = principal.getName();
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                " retrieveUser: {0}",
                e.toString());
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
                e.toString());
        }

        return request;
    }

    public String sendMessage() {
        try {
            logger.info("Entering sendMailBean.sendMessage");

            // Prepare our mail message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender + "@" + server));

            InternetAddress[] dests = new InternetAddress[] {
                    new InternetAddress(recipient + "@" + server)
                };
            message.setRecipients(Message.RecipientType.TO, dests);
            message.setSubject(subject);
            message.setContent(body, "text/plain");

            // Send our mail message
            Transport.send(message);
            logger.info("In sendMailBean.sendMessage: Message sent");
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                "sendMessage failed: {0}",
                e.toString());
            setSendSuccessful(false);
        }

        return "sendresponse";
    }

    public String logout() {
        logger.info("Entering sendMailBean.logout");

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
