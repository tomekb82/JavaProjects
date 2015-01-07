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

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


/**
 *
 * @author ian
 */
public class HelloInterceptor {
    private static final Logger logger = Logger.getLogger(
                "interceptor.ejb.HelloInterceptor");
    protected String greeting;

    public HelloInterceptor() {
    }

    @AroundInvoke
    public Object modifyGreeting(InvocationContext ctx)
        throws Exception {
        Object[] parameters = ctx.getParameters();
        String param = (String) parameters[0];
        param = param.toLowerCase();
        parameters[0] = param;
        ctx.setParameters(parameters);

        try {
            return ctx.proceed();
        } catch (Exception e) {
            logger.warning("Błąd wywołania ctx.proceed w modifyGreeting()");

            return null;
        }
    }
}
