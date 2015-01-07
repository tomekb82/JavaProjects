/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.inbound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.resource.spi.work.SecurityContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.message.callback.CallerPrincipalCallback;
import javax.security.auth.message.callback.PasswordValidationCallback;
import org.glassfish.security.common.PrincipalImpl;


/**
 * @author jagadish
 */
public class MySecurityContext extends SecurityContext {
    static final Logger logger = Logger.getLogger("mailconnector.ra.inbound");
    private String password;
    private String principalName;
    private String userName;
    private Subject subject;

    public MySecurityContext(
        String userName,
        String password,
        String principalName) {
        this.userName = userName;
        this.password = password;
        this.principalName = principalName;
        logger.info("[Inbound:MySecurityContext] constructor");
    }

    @Override
    public void setupSecurityContext(
        CallbackHandler callbackHandler,
        Subject execSubject,
        Subject serviceSubject) {
        execSubject.getPrincipals()
                   .add(new PrincipalImpl(principalName));

        List<Callback> callbacks = new ArrayList<Callback>();

        CallerPrincipalCallback cpc = new CallerPrincipalCallback(
                    execSubject,
                    new PrincipalImpl(principalName));

        logger.log(
                Level.INFO,
                "[Inbound:MySecurityContext] setting caller principal callback with principal : {0}",
                principalName);
        callbacks.add(cpc);

        PasswordValidationCallback pvc = null;
        pvc = new PasswordValidationCallback(
                    execSubject,
                    userName,
                    password.toCharArray());
        logger.log(
                Level.INFO,
                "[Inbound:MySecurityContext] setting password validation callback with user [ {0} ] + password [ {1} ]",
                new Object[] { userName, password });

        callbacks.add(pvc);

        Callback[] callbackArray = new Callback[callbacks.size()];

        try {
            callbackHandler.handle(callbacks.toArray(callbackArray));
        } catch (UnsupportedCallbackException e) {
            debug("exception occurred : " + e.getMessage());
        } catch (IOException e) {
            debug("exception occurred : " + e.getMessage());
        }

        if (!pvc.getResult()) {
            logger.log(
                    Level.INFO,
                    "[Inbound:MySecurityContext] Password validation callback failure for user : {0}",
                    userName);
            throw new IllegalStateException(
                    "User [ " + userName + " ] not authorized to send message");
        } else {
            logger.log(
                    Level.INFO,
                    "[Inbound:MySecurityContext] Password validation callback succeded for user : {0}",
                    userName);
        }
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("{");
        toString.append("userName : ")
                .append(userName);
        toString.append(", password : ")
                .append(password);
        toString.append(", principalName : ")
                .append(principalName);
        toString.append("}");

        return toString.toString();
    }

    public void debug(String message) {
        logger.log(Level.INFO, "[Inbound:MySecurityContext]: {0}", message);
    }
}
