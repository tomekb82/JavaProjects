package com.factory_pattern.pizzas;

import com.factory_pattern.Pizza;
import com.factory_pattern.PizzaIngredientsFactory;
import com.factory_pattern.ingredients.*;

public class ClamPizza extends Pizza{

	PizzaIngredientsFactory ingredientsFactory;
	
	public ClamPizza(PizzaIngredientsFactory ingredientsFactory){
		this.ingredientsFactory = ingredientsFactory;
	}
	
	public void prepare(){
		
		System.out.println("---------------------------------------------------");
		System.out.println("Preparing " + getName());
		
		System.out.print(" - Ingredients: ");
		dough = ingredientsFactory.createDough();
		sauce = ingredientsFactory.createSauce();
		cheese = ingredientsFactory.createCheese();
		clam = ingredientsFactory.createClam();
		
		System.out.println("");
	}
}
