package com.factory_pattern.NY.pizzas;

import com.factory_pattern.Pizza;

public class NYStylePepperoniPizza extends Pizza {

	public void prepare(){
		System.out.println("Preparing " + getName());
	}
	
}
