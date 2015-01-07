/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.web.managedbeans;

import java.io.Serializable;
import java.util.logging.Logger;


public class MailMessage implements Serializable {
    static final Logger logger = Logger.getLogger(
                "mailconnector.web.managedbeans");
    private static final long serialVersionUID = -5460643776216275258L;
    private String body;
    private String recipient;
    private String sender;
    private String subject;
    private short id;

    public MailMessage() {
        logger.info("MailMessage::Constructor(no args)");
    }

    public MailMessage(
        short id,
        String recipient,
        String sender,
        String subject,
        String body) {
        logger.info("MailMessage::Constructor(args)");
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public short getId() {
        logger.info("[MailMessage] Calling getId");

        return id;
    }

    public void setId(short id) {
        logger.info("[MailMessage] Calling setId");
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        logger.info("[MailMessage] Calling getSender");

        return sender;
    }

    public void setSender(String sender) {
        logger.info("[MailMessage] Calling setSender");
        this.sender = sender;
    }

    public String getSubject() {
        logger.info("[MailMessage] Calling getSubject");

        return subject;
    }

    public void setSubject(String subject) {
        logger.info("[MailMessage] Calling setSubject");
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
