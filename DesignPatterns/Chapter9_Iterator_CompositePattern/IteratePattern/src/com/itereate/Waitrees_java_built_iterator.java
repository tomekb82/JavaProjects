package com.itereate;

import java.util.Iterator;

public class Waitrees_java_built_iterator  {

	Menu pancake;
	Menu diner;
	
	public Waitrees_java_built_iterator(Menu pancake, Menu diner){
		this.pancake = pancake;
		this.diner = diner;
	}
	
	public void printMenu(){
		
		Iterator pancakeIterator = pancake.createIterator();
		Iterator dinerIterator = diner.createIterator();
		System.out.println("\nMENU\n------\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerIterator);
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
