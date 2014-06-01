package com.itereate;

import java.util.Hashtable;
import java.util.Iterator;

public class CafeMenu implements Menu {

	Hashtable menuItems = new Hashtable();
	
	public CafeMenu(){
		
		addItem("a", "b" ,true, 2.55);
		addItem("c", "d" ,false, 1.45);
		addItem("e", "f" ,true, 5.25);
	}
	
	public Iterator createIterator(){
		return menuItems.values().iterator();
	}
	
	public void addItem(String name, String description,
						boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		
		menuItems.put(menuItem.getName(), menuItem);
	}
	
	public MenuItem[] getMenuItems(){
		return menuItems;
	}
}
