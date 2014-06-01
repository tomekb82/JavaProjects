package com.itereate;

import java.util.ArrayList;

public class MainApp {

	public static void main(String args[]){
		
		MyMenu pancake = new PancakeHouseMenu();/* My interator */
		MyMenu diner = new DinnerMenu(); 		/* My interator */
		Menu cafe_java = new CafeMenu();  		/* interator from java libs */
		Waitrees waitrees = new Waitrees(pancake, diner,cafe_java);
		waitrees.printMenu();
		
		System.out.println("\n---------------------------------------------------------");
		
		Menu pancake_java = new PancakeHouseMenu_java_built_iterator(); 	/* interator from java libs */
		Menu diner_java = new DinnerMenu_java_built_iterator(); 			/* interator from java libs */
		Waitrees_java_built_iterator waitrees_java = new Waitrees_java_built_iterator(pancake_java, diner_java);
		waitrees_java.printMenu();
		
		System.out.println("\n---------------------------------------------------------");
		
		ArrayList menus_java = new ArrayList();
		menus_java.add(pancake_java);
		menus_java.add(diner_java);
		menus_java.add(cafe_java);
		Waitrees_menus waitrees_menus_java = new Waitrees_menus(menus_java);
		waitrees_menus_java.printMenu();
	}
}
