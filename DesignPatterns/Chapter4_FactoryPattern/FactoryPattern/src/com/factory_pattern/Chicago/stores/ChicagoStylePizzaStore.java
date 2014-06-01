package com.factory_pattern.Chicago.stores;

import com.factory_pattern.Pizza;
import com.factory_pattern.PizzaStore;
import com.factory_pattern.Chicago.pizzas.*;

public class ChicagoStylePizzaStore extends PizzaStore {

	public Pizza createPizza(String type){
		
		Pizza pizza=null;
		
		if(type.equals("cheese")){
			pizza = new ChicagoStyleCheesePizza();
		} else if (type.equals("pepperoni")){
			pizza = new ChicagoStylePepperoniPizza();
		} else if (type.equals("clam")){
			pizza = new ChicagoStyleClamPizza();
		} else if (type.equals("veggie")){
			pizza = new ChicagoStyleVeggiePizza();
		} 
		
		if(pizza != null)
			pizza.setName("Chicago " + type);
		
		return pizza;
	}
}
