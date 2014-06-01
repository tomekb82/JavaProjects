package com.itereate;

import java.util.Iterator;

public class Waitrees {

	MyMenu pancake;
	MyMenu diner;
	Menu cafe;
	
	public Waitrees(MyMenu pancake, MyMenu diner, Menu cafe){
		this.pancake = pancake;
		this.diner = diner;
		this.cafe = cafe;
	}
	
	public void printMenu(){
		
		MyIterator pancakeIterator = pancake.createIterator();
		MyIterator dinerIterator = diner.createIterator();
		Iterator cafeIterator = cafe.createIterator();
		System.out.println("\nMENU\n------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerIterator);
		System.out.println("\nCafe");
		printMenu(cafeIterator);
	}
	
	public void printMenu(MyIterator iterator){
		
		while(iterator.hasNext()){
			MenuItem item = (MenuItem)iterator.next();
			System.out.print(item.getName() + ",");
			System.out.print(item.getPrice() + "--");
			System.out.print(item.getDescription());
		}
	}
	
	public void printMenu(Iterator iterator){
		
		while(iterator.hasNext()){
			MenuItem item = (MenuItem)iterator.next();
			System.out.print(item.getName() + ",");
			System.out.print(item.getPrice() + "--");
			System.out.print(item.getDescription());
		}
	}
}
