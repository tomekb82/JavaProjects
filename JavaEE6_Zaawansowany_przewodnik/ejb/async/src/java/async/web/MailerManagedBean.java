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
package async.web;

import async.ejb.MailerBean;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author ievans
 */
@Named
@RequestScoped
public class MailerManagedBean {
    private static final Logger logger = Logger.getLogger(
                "async.web.MailerManagedBean");
    @EJB
    protected MailerBean mailerBean;
    protected String email;
    protected String status;

    /** Creates a new instance of MailerManagedBean */
    public MailerManagedBean() {
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String send() {
        String response = "response";

        try {
            Future<String> mailStatus = mailerBean.sendMessage(this.getEmail());

            while (!mailStatus.isDone()) {
                this.setStatus("Przetwarzam...");
            }

            try {
                this.setStatus(mailStatus.get());
            } catch (ExecutionException ex) {
                this.setStatus(ex.getCause().toString());
            }
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        return response;
    }
}
