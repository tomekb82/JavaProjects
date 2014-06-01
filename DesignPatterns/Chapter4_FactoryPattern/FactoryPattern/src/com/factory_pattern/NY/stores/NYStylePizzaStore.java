package com.factory_pattern.NY.stores;

import com.factory_pattern.Pizza;
import com.factory_pattern.PizzaStore;
import com.factory_pattern.NY.pizzas.NYStyleCheesePizza;
import com.factory_pattern.NY.pizzas.NYStyleClamPizza;
import com.factory_pattern.NY.pizzas.NYStylePepperoniPizza;
import com.factory_pattern.NY.pizzas.NYStyleVeggiePizza;

public class NYStylePizzaStore extends PizzaStore {

	public Pizza createPizza(String type){
		
		Pizza pizza=null;
		
		if(type.equals("cheese")){
			pizza = new NYStyleCheesePizza();
		} else if (type.equals("pepperoni")){
			pizza = new NYStylePepperoniPizza();
		} else if (type.equals("clam")){
			pizza = new NYStyleClamPizza();
		} else if (type.equals("veggie")){
			pizza = new NYStyleVeggiePizza();
		} 
		
		if(pizza != null)
			pizza.setName("NY(" + type + ")");
		
		return pizza;
	}
}
