package ngdemo.domain.dao.impl;

import ngdemo.domain.dao.HibernateUtil;
import ngdemo.domain.dao.RecipeDao;
import ngdemo.domain.model.Recipe;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/2/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeDaoImpl implements RecipeDao{

    public void save(Recipe recipe){
        System.out.println("RecipeDAO: save()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Long recipeId = (Long) session.save(recipe);

        tx.commit();
        session.close();

    }

    public void update(Recipe recipe){

        System.out.println("RecipeDAO: update()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("update Recipe set recipeDescription = :recipeDescription" +
                " where recipeTitle = :recipeTitle");
        query.setParameter("recipeDescription", recipe.getDescription());
        query.setParameter("recipeTitle", recipe.getTitle());
        int result = query.executeUpdate();

        tx.commit();
        session.close();
    }

    public void delete(Recipe recipe){

        System.out.println("RecipeDAO: delete()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("delete Recipe where recipeTitle = :recipeTitle");
        query.setParameter("recipeTitle", recipe.getTitle());
        int result = query.executeUpdate();

        tx.commit();
        session.close();

    }

    public Recipe findByRecipeTitle(String title){

        System.out.println("RecipeDAO: find()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Recipe where recipeTitle = :recipeTitle" );
        query.setParameter("recipeTitle", title);
        List list = query.list();

        tx.commit();
        session.close();

        if (list.isEmpty())
            return null;

        return (Recipe)list.get(0);
    }
}
