package com.factory_pattern.Chicago.stores;

import com.factory_pattern.*;
import com.factory_pattern.Chicago.ingredients.*;
import com.factory_pattern.pizzas.CheesePizza;
import com.factory_pattern.pizzas.ClamPizza;
import com.factory_pattern.pizzas.PepperoniPizza;
import com.factory_pattern.pizzas.*;

public class ChicagoStylePizzeStoreIng extends PizzaStore {

	public Pizza createPizza(String type){
		
		Pizza pizza=null;
		
		PizzaIngredientsFactory ingredientsFactory = new ChicagoPizzaIngredientsFactory();
		
		if(type.equals("cheese")){
			pizza = new CheesePizza(ingredientsFactory);
			pizza.setName("Chicago Style Cheese Pizza:");
		} else if (type.equals("pepperoni")){
			pizza = new PepperoniPizza(ingredientsFactory);
			pizza.setName("Chicago Style Pepperoni Pizza:");
		} else if (type.equals("clam")){
			pizza = new ClamPizza(ingredientsFactory);
			pizza.setName("Chicago Style Clam Pizza:");
		} else if (type.equals("veggie")){
			pizza = new VeggiePizza(ingredientsFactory);
			pizza.setName("Chicago Style Veggie Pizza:");
		} 
		
		return pizza;
	}
}
