package com.factory_pattern.pizzas;

import com.factory_pattern.Pizza;
import com.factory_pattern.PizzaIngredientsFactory;
import com.factory_pattern.ingredients.*;

public class CheesePizza extends Pizza {
	
	PizzaIngredientsFactory ingredientsFactory;
	
	public CheesePizza(PizzaIngredientsFactory ingredientsFactory){
		this.ingredientsFactory = ingredientsFactory;
	}
	
	public void prepare(){
		
		System.out.println("---------------------------------------------------");
		System.out.println("Preparing " + getName());
		
		System.out.print(" - Ingredients: ");
		dough = ingredientsFactory.createDough();
		sauce = ingredientsFactory.createSauce();
		cheese = ingredientsFactory.createCheese();
		
		System.out.println("");
		
	}

}
