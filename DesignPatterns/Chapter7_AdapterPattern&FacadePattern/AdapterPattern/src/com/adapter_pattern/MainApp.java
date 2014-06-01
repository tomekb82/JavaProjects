package com.adapter_pattern;

public class MainApp {

	public static void main(String[] args){
		
		MallarDuck duck = new MallarDuck();
		WildTurkey turkey = new WildTurkey();
		
		/*
		 * Adapters:
		 */
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		Turkey duckAdapter = new DuckAdapter(duck);
		
		System.out.println("---------------------------------");
		System.out.println("Turkey says...");
		testTurkey(turkey);
		
		System.out.println("---------------------------------");
		System.out.println("Duck says...");
		testDuck(duck);
		
		System.out.println("---------------------------------");
		System.out.println("TurkeyAdapter says...");
		testDuck(turkeyAdapter);
		
		System.out.println("---------------------------------");
		System.out.println("DuckAdapter says...");
		testTurkey(duckAdapter);
		
	}
	
	static void testDuck(Duck duck){
		duck.quack();
		duck.fly();
	}
	
	static void testTurkey(Turkey turkey){
		turkey.gooble();
		turkey.fly();
	}
}
