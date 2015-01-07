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
import com.forest.qualifiers.New;
import com.forest.qualifiers.Paid;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import services.payment.forest.com.ObjectFactory;
import services.payment.forest.com.Payment;
import services.payment.forest.com.PaymentService;


/**
 * CDI event handler that calls Payment service for new orders. It will
 * intercept (observe) an
 * <code>OrderEvent</code> with
 * <code>@New</code> <b>qualifier</b>.
 *
 * @author markito
 * @see com.forest.events.OrderEvent
 */
@Stateless
public class PaymentHandler implements IOrderHandler, Serializable {
    private static final Logger logger = Logger.getLogger(
                PaymentHandler.class.getCanonicalName());
    private static final long serialVersionUID = 4979287107039479577L;

    // systemic user
    private static final String USER = "paymentUser@dukesforest.com";
    private static final String TOKEN = "93f725a07423fe1c889f448b33d21f46";
    @Inject
    @Paid
    Event<OrderEvent> eventManager;
    @EJB
    OrderBean orderBean;

    /**
     * Payment service endpoint
     */
    @WebServiceRef(wsdlLocation = "http://localhost:8080/dukes-payment/PaymentService?wsdl")
    private PaymentService service;

    @Override
    @Asynchronous
    public void onNewOrder(@Observes
    @New
    OrderEvent event) {
        logger.log(
                Level.FINEST,
                "{0} Event being processed by PaymentHandler",
                Thread.currentThread().getName());

        if (processPayment(convertForWS(event))) {
            orderBean.setOrderStatus(
                    event.getOrderID(),
                    OrderBean.Status.PENDING_PAYMENT.getStatus());
            logger.info("Payment Approved");
            eventManager.fire(event);
        } else {
            orderBean.setOrderStatus(
                    event.getOrderID(),
                    OrderBean.Status.CANCELLED_PAYMENT.getStatus());
            logger.info("Payment Denied");
        }
    }

    private Boolean processPayment(
        services.payment.forest.com.OrderEvent order) {
        Payment port = service.getPaymentPort();

        ((BindingProvider) port).getRequestContext()
         .put(BindingProvider.USERNAME_PROPERTY, USER);
        ((BindingProvider) port).getRequestContext()
         .put(BindingProvider.PASSWORD_PROPERTY, TOKEN);

        return port.processPayment(order);
    }

    private services.payment.forest.com.OrderEvent convertForWS(
        OrderEvent event) {
        ObjectFactory obj = new ObjectFactory();
        services.payment.forest.com.OrderEvent newOrder = obj.createOrderEvent();

        newOrder.setAmount(event.getAmount());
        newOrder.setCustomerID(event.getCustomerID());
        //newOrder.setDateCreated(event.getDateCreated());  
        newOrder.setOrderID(event.getOrderID());
        newOrder.setStatusID(event.getStatusID());

        return newOrder;
    }
}
