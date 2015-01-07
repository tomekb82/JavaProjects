/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package producerfields.ejb;

import producerfields.db.UserDatabase;
import producerfields.entity.ToDo;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ConversationScoped
@Stateful
public class RequestBean {
    @Inject
    @UserDatabase
    EntityManager em;

    public ToDo createToDo(String inputString) {
        ToDo toDo;
        Date currentTime = Calendar.getInstance()
                                   .getTime();

        try {
            toDo = new ToDo();
            toDo.setTaskText(inputString);
            toDo.setTimeCreated(currentTime);
            em.persist(toDo);

            return toDo;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<ToDo> getToDos() {
        try {
            List<ToDo> toDos = (List<ToDo>) em.createQuery(
                        "SELECT t FROM ToDo t ORDER BY t.timeCreated")
                                              .getResultList();

            return toDos;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
