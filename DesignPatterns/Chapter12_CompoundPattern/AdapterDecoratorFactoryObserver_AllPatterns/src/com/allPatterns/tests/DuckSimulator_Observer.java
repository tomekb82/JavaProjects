package com.allPatterns.tests;

import com.allPatterns.Quackable;
import com.allPatterns.adapter.GooseAdapter;
import com.allPatterns.composite.Flock_Composite;
import com.allPatterns.decorator.DuckCounterDecorator;
import com.allPatterns.factory.*;
import com.allPatterns.gooses.Goose;
import com.allPatterns.observer.*;

public class DuckSimulator_Observer {
	
		static boolean debug = true;
		
		public static void main(String[] args){
			
			DuckSimulator_Observer simulator = new DuckSimulator_Observer();
			AbstractDuckFactory duckFactory = new CountingDecorator_DuckFactory_Observer(); /* Factory & Decorator & Observer Pattern */
			simulator.simulate(duckFactory);
		}
		
		void simulate(AbstractDuckFactory duckFactory){
			
			Quackable DuckCall = duckFactory.createDuckCall();			/* Factory Pattern */
			Quackable RedheadDuck = duckFactory.createRedheadDuck();	/* Factory Pattern */
			Quackable RubberDuck = duckFactory.createRubberDuck();		/* Factory Pattern */
			/* TEST: Adapter Pattern */
			Quackable gooseDuck = new GooseAdapter(new Goose());
			/* TEST: Composite Pattern */
			Flock_Composite flockOfDuck = new Flock_Composite();
			flockOfDuck.add(DuckCall);
			flockOfDuck.add(RedheadDuck);
			flockOfDuck.add(RubberDuck);
			flockOfDuck.add(gooseDuck);
			
			System.out.println("\nDuck Simulator: Whole Flock without Mallard");
			simulate(flockOfDuck);
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			
			/* --------------- Observer Pattern ---------------------*/
			Quackable MallardDuck = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			System.out.println("\nDuck Simulator: With Observer");
			Quacklogist quackLogist = new Quacklogist();
			MallardDuck.registerObserver(quackLogist);
			MallardDuck.quack();
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			/* ----------------------------------------------------- */
			
			
			/* -------------    Flock of Mallard Duck  ------------- */
			Quackable MallardDuck1 = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			Quackable MallardDuck2 = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			Quackable MallardDuck3 = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			Quackable MallardDuck4 = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			Quackable MallardDuck5 = duckFactory.createMallardDuck(); 	/* Factory & Observer Pattern */
			/* Composite Pattern */
			Flock_Composite flockOfMallards = new Flock_Composite();
			flockOfMallards.add(MallardDuck1);
			flockOfMallards.add(MallardDuck2);
			flockOfMallards.add(MallardDuck3);
			flockOfMallards.add(MallardDuck4);
			flockOfMallards.add(MallardDuck5);
			
			System.out.println("\nDuck Simulator: With Observer - Mallard Flock(only)");
			System.out.println("\n  -------- registering ------------");
			flockOfMallards.registerObserver(quackLogist);
			System.out.println("\n  --------- simulating ------------");
			simulate(flockOfMallards);
			/* ----------------------------------------------------- */
			
			/* TEST: Decorator Pattern */
			System.out.println("\n The ducks quack " + DuckCounterDecorator.getQuacks() + " times.");
			
		}
		
		void simulate(Quackable duck){
			duck.quack();
		}

}
