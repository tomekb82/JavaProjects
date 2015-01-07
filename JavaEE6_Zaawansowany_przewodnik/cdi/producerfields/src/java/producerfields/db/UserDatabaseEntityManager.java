/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package producerfields.db;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Singleton
public class UserDatabaseEntityManager {
    // deklaracja pola produkującego
    @Produces
    @UserDatabase
    @PersistenceContext
    private EntityManager em;

    // użycie metod tworzenia i usuwania pola produkującego
    /* @PersistenceContext
       private EntityManager em;
       @Produces
       @UserDatabase
       public EntityManager create() {
           return em;
       }
       public void close(@Disposes @UserDatabase EntityManager em) {
           em.close();
       } */
}
