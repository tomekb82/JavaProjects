package pl.tb.myApp.repository.util;

import pl.tb.myApp.model.util.BasicEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tomek on 17.10.15.
 */
public class BasicRepository {

    @PersistenceContext(unitName = "MYAPP")
    protected EntityManager em;

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public <T> T merge(T entity) {
        return em.merge(entity);
    }

    public <T> T persist(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> boolean delete(Class<T> entityType, Long id) {
        T entity = em.find(entityType, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    public <T extends BasicEntity> T save(T entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            em.flush();
            return entity;
        } else {
            T mergerdEntity = em.merge(entity);
            em.flush();
            return mergerdEntity;
        }
    }
}
