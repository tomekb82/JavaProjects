/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package async.ejb;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author ievans
 */
@Named
@Stateless
public class MailerBean {
    private static final Logger logger = Logger.getLogger(
                "async.ejb.MailerBean");
    @Resource(name = "mail/myExampleSession")
    private Session session;

    @Asynchronous
    public Future<String> sendMessage(String email) {
        String status;

        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            message.setSubject("Wiadomość testowa z przykładu async");
            message.setHeader("X-Mailer", "JavaMail");

            DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                        DateFormat.LONG,
                        DateFormat.SHORT);
            Date timeStamp = new Date();
            String messageBody = "To wiadomość testowa z przykładowej aplikacji "
                + "z samouczka Javy EE. Została wysłana w dniu "
                + dateFormatter.format(timeStamp) + ".";
            message.setText(messageBody);
            message.setSentDate(timeStamp);
            Transport.send(message);
            status = "Wysłana";
            logger.log(Level.INFO, "Email wysłany do {0}", email);
        } catch (MessagingException ex) {
            logger.severe("Błąd w wysyłce komunikatu.");
            status = "Napotkano błąd: " + ex.getMessage();
            logger.severe(ex.getMessage());
        }

        return new AsyncResult<String>(status);
    }
}
