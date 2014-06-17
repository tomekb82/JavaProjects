package ngdemo.domain.dao.impl;

import ngdemo.domain.dao.HibernateUtil;
import ngdemo.domain.dao.StockDao;
import ngdemo.domain.model.Stock;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StockDaoImpl implements StockDao {


	public void save(Stock stock){

        Session session = HibernateUtil.getSessionFactory().openSession();

        //try {
            session.beginTransaction();
            session.save(stock);
            session.getTransaction().commit();
            session.close();
        //}
        //catch (RuntimeException e) {
        //    session.getTransaction().rollback();
         //   throw e;
        //}

        /* or

        Query query = session.createQuery("insert into Stock(stock_code, stock_name)" +
    			"select stock_code, stock_name from backup_stock");
        int result = query.executeUpdate();
        */

        //getHibernateTemplate().save(stock);
	}
	
	public void update(Stock stock){
		//getHibernateTemplate().update(stock);

        System.out.println("StockDAO: update()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("update Stock set stockName = :stockName" +
                " where stockCode = :stockCode");
        query.setParameter("stockName", stock.getStockName());
        query.setParameter("stockCode", stock.getStockCode());
        int result = query.executeUpdate();

        tx.commit();
        session.close();
	}
	
	public void delete(Stock stock){
		//getHibernateTemplate().delete(stock);
        System.out.println("StockDAO: delete()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("delete Stock where stockCode = :stockCode");
        query.setParameter("stockCode", stock.getStockCode());
        int result = query.executeUpdate();

        tx.commit();
        session.close();

	}
	
	public Stock findByStockCode(String stockCode){
		//List list = getHibernateTemplate().find("from Stock where stockCode=?",stockCode);

        System.out.println("StockDAO: find()");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Stock where stockCode = :code" );
        query.setParameter("code", stockCode);
        List list = query.list();
       /*
        System.out.println( stockk.size() + " stock(s) found:" );
        for ( Iterator iter = stockk.iterator(); iter.hasNext(); ) {
            Stock loadedMsg = (Stock) iter.next();
            System.out.println( loadedMsg.toString() );
        }
        */
        tx.commit();
        session.close();

        if (list.isEmpty())
            return null;

        return (Stock)list.get(0);
	}

}
