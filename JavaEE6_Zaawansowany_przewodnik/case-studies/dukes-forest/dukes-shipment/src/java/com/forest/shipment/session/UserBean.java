/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.shipment.session;

import com.forest.entity.Customer;
import com.forest.entity.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author markito
 */
@Stateless
public class UserBean extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    public UserBean() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Person getUserByEmail(String email) {
        Query createNamedQuery = getEntityManager()
                                     .createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        return (Person) createNamedQuery.getSingleResult();
    }
}
