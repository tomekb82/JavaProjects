package com.factory_pattern.NY.stores;

import com.factory_pattern.*;
import com.factory_pattern.NY.ingredients.NYPizzaIngredientsFactory;
import com.factory_pattern.pizzas.CheesePizza;
import com.factory_pattern.pizzas.ClamPizza;
import com.factory_pattern.pizzas.PepperoniPizza;
import com.factory_pattern.pizzas.*;


public class NYStylePizzaStoreIng extends PizzaStore{

	public Pizza createPizza(String type){
		
		Pizza pizza=null;
		
		PizzaIngredientsFactory ingredientsFactory = new NYPizzaIngredientsFactory();
		
		if(type.equals("cheese")){
			pizza = new CheesePizza(ingredientsFactory);
			pizza.setName("New York Style Cheese Pizza:");
		} else if (type.equals("pepperoni")){
			pizza = new PepperoniPizza(ingredientsFactory);
			pizza.setName("New York Style Pepperoni Pizza:");
		} else if (type.equals("clam")){
			pizza = new ClamPizza(ingredientsFactory);
			pizza.setName("New York Style Clam Pizza:");
		} else if (type.equals("veggie")){
			pizza = new VeggiePizza(ingredientsFactory);
			pizza.setName("New York Style Veggie Pizza:");
		} 
		
		return pizza;
	}
	
}
