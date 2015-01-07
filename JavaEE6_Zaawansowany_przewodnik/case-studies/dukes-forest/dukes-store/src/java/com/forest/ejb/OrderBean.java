/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.ejb;

import com.forest.entity.CustomerOrder;
import com.forest.entity.OrderStatus;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;


/**
 * OrderBean is an EJB exposed as RESTful service Provides methods to manipulate
 * order status and query orders based on specific status.
 *
 * @author markito
 */
@Stateless
@Path("/orders")
public class OrderBean extends AbstractFacade<CustomerOrder>
    implements Serializable {
    /**
     * ***************************************************************************
     * Status orders mapped to ENUM
     */
    public enum Status {
        CANCELLED_MANUAL(6),
        CANCELLED_PAYMENT(5),
        PENDING_PAYMENT(2),
        READY_TO_SHIP(3),
        SHIPPED(4);

        private int status;

        private Status(int pStatus) {
            status = pStatus;
        }

        public int getStatus() {
            return status;
        }
    }

    private static final Logger logger = Logger.getLogger(
                ShoppingCart.class.getCanonicalName());
    private static final long serialVersionUID = -2407971550575800416L;
    CustomerOrder order;
    @EJB
    OrderStatusBean statusBean;
    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    public OrderBean() {
        super(CustomerOrder.class);
    }

    /**
     * **************************************************************************
     * Business methods
     * ***************************************************************************
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CustomerOrder> getOrderByCustomerId(Integer id) {
        Query createNamedQuery = getEntityManager()
                                     .createNamedQuery(
                    "CustomerOrder.findByCustomerId");

        createNamedQuery.setParameter("id", id);

        return createNamedQuery.getResultList();
    }

    @GET
    @Produces({
        "application/xml",
        "application/json"
    })
    public List<CustomerOrder> getOrderByStatus(
        @QueryParam("status")
    int status) {
        Query createNamedQuery = getEntityManager()
                                     .createNamedQuery(
                    "CustomerOrder.findByStatus");

        OrderStatus result = statusBean.find(status);

        createNamedQuery.setParameter(
            "status",
            result.getStatus());

        List<CustomerOrder> orders = createNamedQuery.getResultList();

        return orders;
    }

    @PUT
    @Path("{orderId}")
    @Produces({
        "application/xml",
        "application/json"
    })
    public void setOrderStatus(
        @PathParam("orderId")
    int orderId,
        @QueryParam("status")
    int newStatus) {
        logger.log(
                Level.INFO,
                "Order id:{0} - Status:{1}",
                new Object[] { orderId, newStatus });

        try {
            order = this.find(orderId);

            if (order != null) {
                logger.log(
                        Level.FINEST,
                        "Updating order {0} status to {1}",
                        new Object[] { order.getId(), newStatus });

                OrderStatus oStatus = statusBean.find(newStatus);
                order.setOrderStatus(oStatus);

                em.merge(order);

                logger.info("Order Updated!");
            }
        } catch (Exception ex) {
            logger.log(
                Level.SEVERE,
                ex.getMessage());
        }
    }
}
