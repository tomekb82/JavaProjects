package com.allPatterns.tests;

import com.allPatterns.Quackable;
import com.allPatterns.adapter.GooseAdapter;
import com.allPatterns.decorator.DuckCounterDecorator;
import com.allPatterns.ducks.DuckCall;
import com.allPatterns.ducks.MallardDuck;
import com.allPatterns.ducks.RedheadDuck;
import com.allPatterns.ducks.RubberDuck;
import com.allPatterns.gooses.Goose;
import com.allPatterns.factory.*;

public class DuckSimulator_Factory {

	public static void main(String[] args){
		
		DuckSimulator_Factory simulator = new DuckSimulator_Factory();
		AbstractDuckFactory duckFactory = new CountingDecorator_DuckFactory(); /* Factory & Decorator Pattern */
		simulator.simulate(duckFactory);
	}
	
	void simulate(AbstractDuckFactory duckFactory){
		Quackable MallardDuck = duckFactory.createMallardDuck(); 	/* Factory Pattern */
		Quackable DuckCall = duckFactory.createDuckCall();			/* Factory Pattern */
		Quackable RedheadDuck = duckFactory.createRedheadDuck();	/* Factory Pattern */
		Quackable RubberDuck = duckFactory.createRubberDuck();		/* Factory Pattern */
		
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