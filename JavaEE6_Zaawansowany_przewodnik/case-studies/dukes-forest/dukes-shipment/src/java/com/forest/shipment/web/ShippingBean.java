/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.shipment.web;

import com.forest.entity.CustomerOrder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author markito
 */
@Named
public class ShippingBean implements Serializable {
    public enum Status {
        CANCELLED_MANUAL(6),
        CANCELLED_PAYMENT(5),
        PENDING_PAYMENT(2),
        READY_TO_SHIP(3),
        SHIPPED(4);

        private int status;

        private Status(final int pStatus) {
            status = pStatus;
        }

        public int getStatus() {
            return status;
        }
    }

    public static final Logger logger = Logger.getLogger(
                ShippingBean.class.getCanonicalName());
    public static final String SERVICE_ENDPOINT = "http://localhost:8080/dukes-store/services/orders";
    public static final String MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private static final long serialVersionUID = -2526289536313985021L;
    private List<CustomerOrder> completedOrders;
    private List<CustomerOrder> pendingOrders;

    public String getEndpoint() {
        return SERVICE_ENDPOINT;
    }

    public List<CustomerOrder> listByStatus(final Status status) {
        //TODO: add error treatment
        final ClientResponse response = restClient()
                                            .queryParam(
                    "status",
                    String.valueOf(status.getStatus()))
                                            .accept(MEDIA_TYPE)
                                            .get(ClientResponse.class);

        logger.log(
                Level.FINEST,
                "GET Status response: {0}",
                response.getStatus());

        List<CustomerOrder> entity = (List<CustomerOrder>) response.getEntity(
                    new GenericType<List<CustomerOrder>>() {
                    });

        setPendingOrders(entity);

        return entity;
    }

    public void updateOrderStatus(
        final int orderId,
        final Status status) {
        final ClientResponse response = restClient()
                                            .path("/" + orderId)
                                            .queryParam(
                    "status",
                    String.valueOf(status.getStatus()))
                                            .accept(MEDIA_TYPE)
                                            .put(ClientResponse.class);

        logger.log(
                Level.FINEST,
                "PUT Status response: {0}",
                response.getStatus());

        //List<CustomerOrder> entity = (List<CustomerOrder>) response.getEntity(new GenericType<List<CustomerOrder>>() {});      
    }

    public WebResource restClient() {
        final Client client = Client.create();

        return client.resource(getEndpoint());
    }

    /**
     * @return the orders
     */
    public List<CustomerOrder> getPendingOrders() {
        if (pendingOrders == null) {
            pendingOrders = listByStatus(Status.READY_TO_SHIP);
        }

        return pendingOrders;
    }

    /**
     * @param orders the orders to set
     */
    public void setPendingOrders(List<CustomerOrder> orders) {
        this.pendingOrders = orders;
    }

    public void setCompletedOrders(List<CustomerOrder> orders) {
        this.completedOrders = orders;
    }

    public List<CustomerOrder> getCompletedOrders() {
        if (completedOrders == null) {
            completedOrders = listByStatus(Status.SHIPPED);
        }

        return completedOrders;
    }
}
