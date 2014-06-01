package com.factory_pattern.Chicago.pizzas;

import com.factory_pattern.*;

public class ChicagoStyleClamPizza extends Pizza {

	public void prepare(){
		System.out.println("Preparing " + getName());
	}
	
}
