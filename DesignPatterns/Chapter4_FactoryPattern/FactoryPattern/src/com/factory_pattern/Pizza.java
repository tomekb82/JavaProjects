package com.factory_pattern;

import com.factory_pattern.ingredients.*;

public abstract class Pizza {

	String name;
	public Dough dough;
	public Sauce sauce;
	public Cheese cheese;
	public Pepperoni pepperoni;
	public Clams clam;
	public Veggies veggies[];	
	
	public abstract void prepare();
	
	void bake(){
		System.out.println(" - Baking for 25 mintes at 350...");
	}

	void cut(){
		System.out.println(" - Cutting the pizza into diagonal slices...");
	}
	
	void box(){
		System.out.println(" - Placing pizza in official PizzaStore box...");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
