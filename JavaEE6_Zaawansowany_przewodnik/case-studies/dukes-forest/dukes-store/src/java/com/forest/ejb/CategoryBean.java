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
package com.forest.ejb;

import com.forest.entity.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author markito
 */
@Stateless
public class CategoryBean extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    public CategoryBean() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
