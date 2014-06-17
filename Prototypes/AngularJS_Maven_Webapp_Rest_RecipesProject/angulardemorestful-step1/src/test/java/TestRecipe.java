import ngdemo.domain.bo.RecipeBo;
import ngdemo.domain.bo.StockBo;
import ngdemo.domain.bo.TaskBo;
import ngdemo.domain.dao.HibernateUtil2;
import ngdemo.domain.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class TestRecipe {

    Recipe recipe;
    Ingredient ingredient;
    //Stock stock;

    @Before
    public void setUp() {
        recipe = new Recipe();
        ingredient = new Ingredient();

        //stock = new Stock();


        /*
        Stock stock1 = new Stock();
        stock1.setStockCode("3668");
        stock1.setStockName("BBBB");
        stockBo.save(stock1);

        Stock stock2 = stockBo.findByStockCode(stock.getStockCode());
        System.out.println(stock2);

        Stock stock3 = new Stock();
        stock3.setStockCode(stock.getStockCode());
        stock3.setStockName("AAAAAAlllll");
        stockBo.update(stock3);
        System.out.println(stockBo.findByStockCode(stock.getStockCode()));

        stockBo.delete(stock);
        //assertTrue(stockBo.findByStockCode(stock.getStockCode().equals(null));
        //System.out.println("null:"  + nullStock );
          */
    }

    public void addUser(String firstName, String lastName) {

        Transaction trns = null;
        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();

            User2 user = new User2();

            user.setFirstName(firstName);
            user.setLastName(lastName);

            session.save(user);

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

    public void addTask(int userID, String title, String description) {

        Transaction trns = null;

        Session session = HibernateUtil2.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();

            Task task = new Task();

            task.setUserID(userID);
            task.setTitle(title);
            task.setDescription(description);

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

    public void updateLastName(int id, String lastName) {
        Transaction trns = null;
        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hqlUpdate = "update User2 u set u.lastName = :newLastName where u.id = :oldId";
            int updatedEntities = session.createQuery( hqlUpdate )
                    .setString( "newLastName", lastName )
                    .setInteger( "oldId", id )
                    .executeUpdate();

            trns.commit();
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

    public void updateUser(User2 user) {
        Transaction trns = null;
        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();

            session.update(user);

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

    public void getFullName(String firstName) {
        Transaction trns = null;
        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            List<User2> users = session.createQuery("from User2 as u where u.firstName = :firstName")
                    .setString( "firstName", firstName )
                    .list();
            for (Iterator<User2> iter = users.iterator(); iter.hasNext();) {
                User2 user = iter.next();
                System.out.println(user.getFirstName() +" " + user.getLastName());
            }
            trns.commit();
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

    public void deleteUser(User2 user) {
        Transaction trns = null;
        Session session = HibernateUtil2.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();

            session.delete(user);

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

    @Test
    public void testSetters() {
        System.out.println("testSetters");

    /*    *//**
         * adding records
         *//*
        addUser("Saranga", "Rath");
        addUser("Isuru", "Sampath");
        addUser("Saranga", "Jaya");
        addUser("Prasanna", "Milinda");



        *//**
         *  retrieving data
         *//*
        getFullName("Saranga");

        *//**
         * full updating records
         *//*

        User2 user = new User2();
        user.setId(1);
        user.setFirstName("Saranga");
        user.setLastName("Rathnayake");
        updateUser(user);

        *//**
         * partial updating records
         *//*
        updateLastName(3, "Jayamaha");

        *//**
         * deleting records
         *//*

        User2 user1 = new User2();
        user1.setId(4);
        deleteUser(user1);*/





        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");


        /* Stock */
        StockBo stockBo = (StockBo)appContext.getBean("stockBo");

        Stock stock = new Stock();
        stock.setStockCode("7668");
        stock.setStockName("HAIO");
        stockBo.save(stock);

        /* Recipe */
        RecipeBo recipeBo = (RecipeBo)appContext.getBean("recipeBo");

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeTitle("ss");
        recipe1.setRecipeDescription("vv");
        recipe1.setRecipeInstructions("yyy");
        recipeBo.save(recipe1);

        /* Task */
        TaskBo taskBo = (TaskBo)appContext.getBean("taskBo");

        Task task = new Task();
        task.setDescription("rrrrr");
        task.setTitle("ddddddd");
        task.setUserID(24);
        taskBo.save(task);


    /*    recipe.setTitle("zupa");
        assertFalse("zzupa".equals(recipe.getTitle()));
        assertEquals("zupa",recipe.getTitle());

        recipe.setDescription("opis");
        assertEquals("opis",recipe.getDescription());

        recipe.setId(12);
        assertEquals("12",recipe.getId());

        recipe.setInstructions("instr1");
        assertEquals("instr1",recipe.getInstructions());

        ingredient.setIngredientName("pomidory");
        assertEquals("pomidory",ingredient.getIngredientName());
        ingredient.setAmount(5);
        assertEquals("5",ingredient.getAmount().toString());
        ingredient.setAmountUnits("paczka");
        assertEquals("paczka",ingredient.getAmountUnits());
        recipe.setIngredient(ingredient);
        assertTrue(recipe.getIngredients().contains(ingredient));*/

    }
}