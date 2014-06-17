package ngdemo.domain.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/1/13
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {


    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory=new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException ex){//Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("util: save() err ");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        // Alternatively, you could look up in JNDI here
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
