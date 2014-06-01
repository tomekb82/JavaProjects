package com.itereate;

import java.util.*;

public class PancakeHouseMenu implements MyMenu {

	ArrayList menuItems;
	
	public PancakeHouseMenu(){
		
		menuItems = new ArrayList();	
		
		addItem("a", "b" ,true, 2.55);
		addItem("c", "d" ,false, 1.45);
		addItem("e", "f" ,true, 5.25);
	}
	
	public MyIterator createIterator(){
		return new PancakeHouseIterator(menuItems);
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
