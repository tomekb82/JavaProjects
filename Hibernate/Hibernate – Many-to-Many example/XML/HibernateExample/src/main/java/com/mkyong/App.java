package com.mkyong;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import com.mkyong.stock.Category;
import com.mkyong.stock.Stock;
import com.mkyong.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate many to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");
        
        Stock stock2 = new Stock();
        stock2.setStockCode("4444");
        stock2.setStockName("ababa");
 
        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");
    
        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);
        Set<Category> categories2 = new HashSet<Category>();
        categories2.add(category1);
        
        stock.setCategories(categories);
        stock2.setCategories(categories2);
        
        
        session.save(stock);
        session.save(stock2);
    
		session.getTransaction().commit();
		System.out.println("Done");
	}
}
