/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package mailconnector.ra.outbound;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;


/**
 * Utilities. The following methods handle authentication.
 *
 *  Note: If subject is null, credential is created from
 *        the ConnectionRequestInfo user/password info.
 *        Otherwise it is created using the subject object.
 *
 * @author Alejandro E. Murillo
 */
public class Util {
    static final Logger logger = Logger.getLogger("mailconnector.ra.outbound");
    static ResourceBundle resource = ResourceBundle.getBundle("/LocalStrings");

    /**
     * Returns a PasswordCredential.
     *
     * @param mcf  a ManagedConnectionFactory object
     * @param subject  security context as JAAS subject
     * @param info  ConnectionRequestInfo instance
     *
     * @return a PasswordCredential
     */
    public static PasswordCredential getPasswordCredential(
        final ManagedConnectionFactory mcf,
        final Subject subject,
        ConnectionRequestInfo info) throws ResourceException {
        if (subject == null) {
            if (info == null) {
                logger.info(
                        "Outbound:Util::getPasswordCredential: INFO is null");

                return null;
            } else {
                ConnectionRequestInfoImpl myinfo = (ConnectionRequestInfoImpl) info;

                // Can't create a PC with null values
                if ((myinfo.getUserName() == null)
                        || (myinfo.getPassword() == null)) {
                    logger.info(
                            "Outbound:Util::getPasswordCredential: User or password is null");

                    return null;
                }

                char[] password = myinfo.getPassword()
                                        .toCharArray();

                PasswordCredential pc = new PasswordCredential(
                            myinfo.getUserName(),
                            password);

                pc.setManagedConnectionFactory(mcf);
                logger.info(
                        "Outbound:Util::getPasswordCredential: returning a created PC");

                return pc;
            }
        } else {
            PasswordCredential pc = (PasswordCredential) AccessController
                .doPrivileged(
                        new PrivilegedAction() {
                            @Override
                            public Object run() {
                                Set creds = subject.getPrivateCredentials(
                                            PasswordCredential.class);
                                Iterator iter = creds.iterator();

                                while (iter.hasNext()) {
                                    PasswordCredential temp = (PasswordCredential) iter
                                        .next();

                                    if ((temp != null)
                                            && (temp.getManagedConnectionFactory() != null)
                                            && temp.getManagedConnectionFactory()
                                                       .equals(mcf)) {
                                        return temp;
                                    }
                                }

                                return null;
                            }
                        });

            if (pc == null) {
                throw new SecurityException(
                        resource.getString("util.no_credential"));
            } else {
                logger.info(
                        "Outbound:Util::getPasswordCredential: returning a FOUND PC");

                return pc;
            }
        }
    }

    /**
     * Determines whether two strings are the same.
     *
     * @param a  first string
     * @param b  second string
     *
     * @return  true if the two strings are equal; false otherwise
     */
    public static boolean isEqual(
        String a,
        String b) {
        if (a == null) {
            return (b == null);
        } else {
            return a.equals(b);
        }
    }

    /**
     * Determines whether two PasswordCredentials are the same.
     *
     * @param a  first PasswordCredential
     * @param b  second PasswordCredential
     *
     * @return  true if the two parameters are equal; false otherwise
     */
    public static boolean isPasswordCredentialEqual(
        PasswordCredential a,
        PasswordCredential b) {
        if (a == b) {
            return true;
        }

        if ((a == null) && (b != null)) {
            return false;
        }

        if ((a != null) && (b == null)) {
            return false;
        }

        if (!isEqual(
                    a.getUserName(),
                    b.getUserName())) {
            return false;
        }

        String p1 = null;
        String p2 = null;

        if (a.getPassword() != null) {
            p1 = new String(a.getPassword());
        }

        if (b.getPassword() != null) {
            p2 = new String(b.getPassword());
        }

        return (isEqual(p1, p2));
    }
}
