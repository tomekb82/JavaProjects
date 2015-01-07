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

import java.util.Calendar;


/**
 *
 * @author ian
 */
public class CalendarUtil {
    public CalendarUtil() {
    }

    public static void stripTime(final Calendar cal) {
        // remove all the time information
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
    }
}
