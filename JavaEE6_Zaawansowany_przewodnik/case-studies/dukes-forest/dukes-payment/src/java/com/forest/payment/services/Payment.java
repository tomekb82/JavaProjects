/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.payment.services;

import com.forest.events.OrderEvent;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(targetNamespace = "com.forest.payment.services")
public class Payment {
    /**
     * Web service operation
     */
    private Boolean validateOrder(final OrderEvent order) {
        return (order.getAmount() < 1000);
    }

    @WebMethod(operationName = "processPayment")
    public Boolean processPayment(
        @WebParam(name = "order")
    final OrderEvent order) {
        // validateOrder...
        return validateOrder(order);
    }
}
