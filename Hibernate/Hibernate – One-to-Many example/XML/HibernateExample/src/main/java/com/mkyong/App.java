package com.mkyong;

import java.util.Date;

import org.hibernate.Session;

import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDailyRecord;
import com.mkyong.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");
        session.save(stock);
        
        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());
        
        StockDailyRecord stockDailyRecords2 = new StockDailyRecord();
        stockDailyRecords2.setPriceOpen(new Float("2.3"));
        stockDailyRecords2.setPriceClose(new Float("2.3"));
        stockDailyRecords2.setPriceChange(new Float("20.3"));
        stockDailyRecords2.setVolume(4000000L);
        Date date = new Date();
        date.setHours(23);
        date.setYear(2022);
        stockDailyRecords2.setDate(date);
        
        stockDailyRecords.setStock(stock); 
        stockDailyRecords2.setStock(stock);
        
        stock.getStockDailyRecords().add(stockDailyRecords);
        stock.getStockDailyRecords().add(stockDailyRecords2);

        session.save(stockDailyRecords);
        session.save(stockDailyRecords2);

		session.getTransaction().commit();
		System.out.println("Done");
	}
}
