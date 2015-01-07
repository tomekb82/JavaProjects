/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import sb.PublisherRemote;


/**
 * The MyAppClient class is the client program for this
 * application.  It calls the publisher's publishNews method
 * twice.
 */
public class MyAppClient {
    static final Logger logger = Logger.getLogger("MyAppClient");
    @EJB(name = "PublisherRemote")
    private static PublisherRemote publisher;

    public static void main(String[] args) {
        MyAppClient client = new MyAppClient();
        client.doTest();
        System.exit(0);
    }

    public void doTest() {
        try {
            publisher.publishNews();
            publisher.publishNews();
            System.out.println("Aby sprawdziæ wynik dzia³ania,");
            System.out.println(
                    " zajrzyj do pliku <install_dir>/domains/domain1/logs/server.log.");
        } catch (Exception ex) {
            logger.log(
                    Level.SEVERE,
                    "MyAppClient.doTest: Wyj¹tek: {0}",
                    ex.toString());
        }
    }
}
