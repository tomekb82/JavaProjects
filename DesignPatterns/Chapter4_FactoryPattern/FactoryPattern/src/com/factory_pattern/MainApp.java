package com.factory_pattern;

import com.factory_pattern.*;
import com.factory_pattern.NY.ingredients.*;
import com.factory_pattern.Chicago.ingredients.*;
import com.factory_pattern.NY.stores.*;
import com.factory_pattern.Chicago.stores.*;

public class MainApp {

	public static void main(String[] args){
		
		//TEST3: Ingredients Stores
		PizzaStore st1 = new NYStylePizzaStoreIng();
		st1.orderPizza("cheese");
		st1.orderPizza("clam");
		st1.orderPizza("pepperoni");
		st1.orderPizza("veggie");
		
		PizzaStore st2 = new ChicagoStylePizzeStoreIng();
		st2.orderPizza("cheese");
		st2.orderPizza("clam");
		st2.orderPizza("pepperoni");
		st2.orderPizza("veggie");
		
		
		//TEST1: Stores
				/*
				PizzaStore st1 = new NYStylePizzaStore();
				st1.orderPizza("cheese");
				st1.orderPizza("clam");
				st1.orderPizza("pepperoni");
				st1.orderPizza("veggie");
				
				PizzaStore st2 = new ChicagoStylePizzaStore();
				st2.orderPizza("cheese");
				st2.orderPizza("clam");
				st2.orderPizza("pepperoni");
				st2.orderPizza("veggie");
				*/
				
				//TEST2: Ingredients
				/*
						PizzaIngredientsFactory f1 = new NYPizzaIngredientsFactory();
						f1.createSauce();
						f1.createDough();
						f1.createPepperoni();
						f1.createClam();
						//f1.createVeggies();
						
						PizzaIngredientsFactory f2 = new ChicagoPizzaIngredientsFactory();
						f2.createSauce();
						//f2.createDough();
						f2.createPepperoni();
						f2.createClam();
						//f2.createVeggies();
						*/
		
		
		
		
	}
}
