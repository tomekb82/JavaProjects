package ngdemo.domain.dao.impl;

import ngdemo.domain.dao.HibernateUtil2;
import ngdemo.domain.dao.TaskDao;
import ngdemo.domain.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/3/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskDaoImpl  implements TaskDao{

    @Override
    public void save(Task t) {
        //To change body of implemented methods use File | Settings | File Templates.

        Transaction trns = null;

        Session session = HibernateUtil2.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();

            Task task = new Task();

            task.setUserID(t.getUserID());
            task.setTitle(t.getTitle());
            task.setDescription(t.getDescription());

            session.save(task);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trns != null){
                trns.rollback();
            }
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }
    }

    @Override
    public void update(Task task) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Task task) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Task findByTaskName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
