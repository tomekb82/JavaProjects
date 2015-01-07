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
package dukestutoring.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author ian
 */
public enum StatusType {
    IN,
    OUT,
    PARK;
    public String toString(Locale locale) {
        ResourceBundle res = ResourceBundle.getBundle(
                    "dukestutoring.util.StatusMessages",
                    locale);

        return res.getString(name() + ".string");
    }

    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle(
                    "dukestutoring.util.StatusMessages",
                    locale);

        return res.getString(name() + ".string");
    }
}
