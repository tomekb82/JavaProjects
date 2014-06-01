package com.factory_pattern.NY.ingredients;

import com.factory_pattern.*;
import com.factory_pattern.ingredients.*;

public class NYPizzaIngredientsFactory implements PizzaIngredientsFactory {

	public NYPizzaIngredientsFactory(){
		
	}
	
	public Dough createDough(){
		return new ThinCrustDough();
	}
	
	public Sauce createSauce(){
		return new MarinaraSauce();
	}
	
	public Cheese createCheese(){
		return new ReggianoCheese();
	}
	
	public Veggies[] createVeggies(){
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom()};
		return veggies;
	}
	
	
	public Pepperoni createPepperoni(){
		return new SlicePepperoni();
	}

	public Clams createClam(){
		return new FreshClams();
	}
}
