package com.composite;

import java.util.Iterator;;

public class MainApp {

	public static void main(String args[]){
		
		MenuComponent pancake = new Menu("a", "b");
		MenuComponent diner = new Menu("c", "d");
		MenuComponent cafe = new Menu("e", "f");
		
		MenuComponent dessert = new Menu("g", "h");
		
		
		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
		
		allMenus.add(pancake);
		allMenus.add(diner);
		allMenus.add(cafe);
		
		
		diner.add(new MenuItem("1", "2", true, 3.0));
		diner.add(new MenuItem("3", "4", false, 1.22));
		diner.add(new MenuItem("5", "6", false, 4.44));
		diner.add(new MenuItem("7", "8", true, 2.67));
		
		cafe.add(dessert);
		dessert.add(new MenuItem("9", "10", true, 4.77));
		
		Waitress waitress = new Waitress(allMenus);
		//waitress.printMenu();
		//waitress.printVegetarianMenu();
		testCompostiteIterator(diner);
		testCompostiteIterator(pancake);
		testCompostiteIterator(cafe);
	}
	
	public static void testCompostiteIterator(MenuComponent component){
		
		CompositeIterator iterator = new CompositeIterator(component.createIterator());
		
		while(iterator.hasNext()){
			MenuComponent menuComponent = (MenuComponent)iterator.next();
			menuComponent.print();
		}
		
	}
}
