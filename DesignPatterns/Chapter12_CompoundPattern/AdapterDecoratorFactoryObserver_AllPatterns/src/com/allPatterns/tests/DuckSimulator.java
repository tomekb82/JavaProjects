package com.allPatterns.tests;

import com.allPatterns.Quackable;
import com.allPatterns.adapter.GooseAdapter;
import com.allPatterns.decorator.DuckCounterDecorator;
import com.allPatterns.ducks.*;
import com.allPatterns.gooses.*;

public class DuckSimulator {

	public static void main(String[] args){
		
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulate();
	}
	
	void simulate(){
		Quackable MallardDuck = new DuckCounterDecorator(new MallardDuck()); 	/* Decorator Pattern */
		Quackable DuckCall = new DuckCounterDecorator(new DuckCall());			/* Decorator Pattern */
		Quackable RedheadDuck = new DuckCounterDecorator(new RedheadDuck());	/* Decorator Pattern */
		Quackable RubberDuck = new DuckCounterDecorator(new RubberDuck());		/* Decorator Pattern */
		
		/* TEST: Adapter Pattern */
		Quackable gooseDuck = new GooseAdapter(new Goose());
		
		System.out.println("\nDuck Simulator");
		
		simulate(MallardDuck);
		simulate(DuckCall);
		simulate(RedheadDuck);
		simulate(RubberDuck);
		
		simulate(gooseDuck); /* Adapter Pattern */
		
		/* TEST: Decorator Pattern */
		System.out.println("The ducks quack " + DuckCounterDecorator.getQuacks() + " times.");
		
	}
	
	void simulate(Quackable duck){
		duck.quack();
	}
	
}
