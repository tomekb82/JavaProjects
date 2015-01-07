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
package interceptor.ejb;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;


/**
 *
 * @author ian
 */
@Stateless
@Named
public class HelloBean {
    protected String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    @Interceptors(HelloInterceptor.class)
    public void setName(String name) {
        this.name = name;
    }
}
