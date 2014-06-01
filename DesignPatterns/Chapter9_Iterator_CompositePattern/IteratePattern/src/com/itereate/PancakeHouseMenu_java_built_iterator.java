package com.itereate;

import java.util.ArrayList;
import java.util.Iterator;

public class PancakeHouseMenu_java_built_iterator implements Iterable, Menu {

	ArrayList menuItems;
	
	public PancakeHouseMenu_java_built_iterator(){
		
		menuItems = new ArrayList();	
		
		addItem("a", "b" ,true, 2.55);
		addItem("c", "d" ,false, 1.45);
		addItem("e", "f" ,true, 5.25);
	}
	
	public Iterator createIterator(){
		return menuItems.iterator();
	}
	
	public void addItem(String name, String description,
						boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}
	
	public ArrayList getMenuItems(){
		return menuItems;
	}
	
}
