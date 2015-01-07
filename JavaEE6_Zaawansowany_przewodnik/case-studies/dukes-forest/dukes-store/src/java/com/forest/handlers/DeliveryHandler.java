/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.handlers;

import com.forest.ejb.OrderBean;
import com.forest.events.OrderEvent;
import com.forest.qualifiers.Paid;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;


/**
 *
 * @author markito
 */
@Stateless
public class DeliveryHandler implements IOrderHandler, Serializable {
    private static final Logger logger = Logger.getLogger(
                DeliveryHandler.class.getCanonicalName());
    private static final long serialVersionUID = 4346750267714932851L;
    @EJB
    OrderBean orderBean;

    @Override
    @Asynchronous
    public void onNewOrder(@Observes
    @Paid
    OrderEvent event) {
        logger.log(
                Level.FINEST,
                "{0} Event being processed by DeliveryHandler",
                Thread.currentThread().getName());

        try {
            logger.log(
                    Level.INFO,
                    "Order #{0} has been paid amount of {1}. Order ready to delivery!",
                    new Object[] { event.getOrderID(), event.getAmount() });

            orderBean.setOrderStatus(
                    event.getOrderID(),
                    OrderBean.Status.READY_TO_SHIP.getStatus());
        } catch (Exception jex) {
            logger.log(Level.SEVERE, null, jex);
        }
    }
}
