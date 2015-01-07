/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.api;

import javax.mail.Message;
import javax.resource.ResourceException;


/**
 * Application-level connection handle that is used by a
 * client component to access an EIS instance.
 *
 * @author Alejandro E. Murillo
 */
public interface JavaMailConnection {
    /**
     * Fetches new messages from the mail server. Application-specific method.
     *
     * @return an array of messages
     */
    public Message[] getNewMessages() throws ResourceException;

    /**
     * Fetches new message headers from the mail server. Application-specific
     * method.
     *
     * @return a String array of message headers
     */
    public String[] getNewMessageHeaders() throws ResourceException;

    /**
     * Closes the connection.
     */
    public void close() throws ResourceException;
}
