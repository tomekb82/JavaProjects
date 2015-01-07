/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.ejb;

import com.forest.entity.Groups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author ievans
 */
@Stateless
public class GroupsBean extends AbstractFacade<Groups> {
    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    public GroupsBean() {
        super(Groups.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
