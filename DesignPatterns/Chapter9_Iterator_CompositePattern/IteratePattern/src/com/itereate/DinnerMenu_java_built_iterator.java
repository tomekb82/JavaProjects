package com.itereate;

import java.util.Iterator;

public class DinnerMenu_java_built_iterator implements Iterable, Menu {

	static final int MAX_ITEMS = 6;
	int numberOfItems=0;
	MenuItem[] menuItems;
	
	public DinnerMenu_java_built_iterator(){
		
		menuItems = new MenuItem[MAX_ITEMS];	
		
		addItem("a", "b" ,true, 2.55);
		addItem("c", "d" ,false, 1.45);
		addItem("e", "f" ,true, 5.25);
	}
	
	public Iterator createIterator(){
		return new DinnerMenuIterator_java_built_iterator(menuItems).iterator();
	}
	
	public void addItem(String name, String description,
						boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		assert(numberOfItems < MAX_ITEMS);
		
		menuItems[numberOfItems] = menuItem;
		numberOfItems = numberOfItems+1;
	}
	
	public MenuItem[] getMenuItems(){
		return menuItems;
	}
}

