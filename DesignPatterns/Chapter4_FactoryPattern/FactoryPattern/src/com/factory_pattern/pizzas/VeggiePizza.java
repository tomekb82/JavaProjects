package com.factory_pattern.pizzas;

import com.factory_pattern.Pizza;
import com.factory_pattern.PizzaIngredientsFactory;

public class VeggiePizza extends Pizza{

    PizzaIngredientsFactory ingredientsFactory;
	
	public VeggiePizza(PizzaIngredientsFactory ingredientsFactory){
		this.ingredientsFactory = ingredientsFactory;
	}
	
	public void prepare(){
		
		System.out.println("---------------------------------------------------");
		System.out.println("Preparing " + getName());
		
		System.out.print(" - Ingredients: ");
		dough = ingredientsFactory.createDough();
		sauce = ingredientsFactory.createSauce();
		cheese = ingredientsFactory.createCheese();
		veggies = ingredientsFactory.createVeggies();
		
		System.out.println("");
	}
}
