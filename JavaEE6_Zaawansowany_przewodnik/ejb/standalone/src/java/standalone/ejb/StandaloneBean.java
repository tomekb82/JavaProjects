/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package standalone.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;


@Stateless
public class StandaloneBean {
    private static final String message = "Witamy!";

    public String returnMessage() {
        return message;
    }
}
