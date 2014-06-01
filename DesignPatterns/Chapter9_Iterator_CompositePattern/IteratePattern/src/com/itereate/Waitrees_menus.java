package com.itereate;
import java.util.ArrayList;

import java.util.Iterator;

public class Waitrees_menus {

	ArrayList menus;
	
	public Waitrees_menus(ArrayList menus){
		this.menus = menus;
	}
	
	public void printMenu(){
		
		Iterator menuIterator = menus.iterator();
		int pos = 0;
		
		while(menuIterator.hasNext()){
			Menu menu = (Menu)menuIterator.next();
			System.out.println("\n MENU " + pos);
			printMenu(menu.createIterator());
			pos = pos + 1;
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
