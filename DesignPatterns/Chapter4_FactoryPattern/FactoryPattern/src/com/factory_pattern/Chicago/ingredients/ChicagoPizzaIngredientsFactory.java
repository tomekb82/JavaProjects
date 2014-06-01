package com.factory_pattern.Chicago.ingredients;

import com.factory_pattern.*;
import com.factory_pattern.NY.ingredients.Garlic;
import com.factory_pattern.NY.ingredients.Mushroom;
import com.factory_pattern.NY.ingredients.Onion;
import com.factory_pattern.ingredients.*;

public class ChicagoPizzaIngredientsFactory implements PizzaIngredientsFactory {


	public ChicagoPizzaIngredientsFactory(){
		
	}
	
	public Dough createDough(){
		return new ThickCrustDough();
	}
	
	public Sauce createSauce(){
		return new PlumTomatoSauce();
	}
	
	public Cheese createCheese(){
		return new MozarellaCheese();
	}
	
	public Veggies[] createVeggies(){
		Veggies veggies[] = { new BlackOlives(), new Spinach()};
		return veggies;
	}
	
	
	public Pepperoni createPepperoni(){
		return new SlicedPepperoni();
	}

	public Clams createClam(){
		return new FrozenClams();
	}
}
