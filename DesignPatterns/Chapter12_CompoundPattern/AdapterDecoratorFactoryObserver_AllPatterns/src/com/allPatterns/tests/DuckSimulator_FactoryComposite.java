package com.allPatterns.tests;

import com.allPatterns.Quackable;
import com.allPatterns.adapter.GooseAdapter;
import com.allPatterns.composite.Flock_Composite;
import com.allPatterns.decorator.DuckCounterDecorator;
import com.allPatterns.factory.AbstractDuckFactory;
import com.allPatterns.factory.CountingDecorator_DuckFactory;
import com.allPatterns.gooses.Goose;

public class DuckSimulator_FactoryComposite {

		public static void main(String[] args){
			
			DuckSimulator_FactoryComposite simulator = new DuckSimulator_FactoryComposite();
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
			
			/* TEST: Composite Pattern */
			Flock_Composite flockOfDuck = new Flock_Composite();
			flockOfDuck.add(MallardDuck);
			flockOfDuck.add(DuckCall);
			flockOfDuck.add(RedheadDuck);
			flockOfDuck.add(RubberDuck);
			flockOfDuck.add(gooseDuck);
			
			/* -------------    Flock of Mallard Duck  ------------- */
			Quackable MallardDuck1 = duckFactory.createMallardDuck(); 	/* Factory Pattern */
			Quackable MallardDuck2 = duckFactory.createMallardDuck(); 	/* Factory Pattern */
			Quackable MallardDuck3 = duckFactory.createMallardDuck(); 	/* Factory Pattern */
			Quackable MallardDuck4 = duckFactory.createMallardDuck(); 	/* Factory Pattern */
			Quackable MallardDuck5 = duckFactory.createMallardDuck(); 	/* Factory Pattern */
			/* Composite Pattern */
			Flock_Composite flockOfMallards = new Flock_Composite();
			flockOfMallards.add(MallardDuck1);
			flockOfMallards.add(MallardDuck2);
			flockOfMallards.add(MallardDuck3);
			flockOfMallards.add(MallardDuck4);
			flockOfMallards.add(MallardDuck5);
			
			flockOfDuck.add(flockOfMallards);
			/* ----------------------------------------------------- */

			
			System.out.println("\nDuck Simulator: Whole Flock");
			simulate(flockOfDuck);
			
			System.out.println("\nDuck Simulator: Mallard Flock(only)");
			simulate(flockOfMallards);
			
			/* TEST: Decorator Pattern */
			System.out.println("The ducks quack " + DuckCounterDecorator.getQuacks() + " times.");
			
		}
		
		void simulate(Quackable duck){
			duck.quack();
		}
}
