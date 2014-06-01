package com.allPatterns.decorator;

import com.allPatterns.Quackable;
import com.allPatterns.observer.*;
import com.allPatterns.ducks.*;

public class DuckCounterDecorator implements Quackable {
	
	Quackable duck;
	static int numberOfQuacks;
	boolean debug = true;
	
	public DuckCounterDecorator (Quackable duck){
		if(debug){
			System.out.println("Decorator: creating quacker - " + duck.getClass().getSimpleName());
		}
		this.duck = duck;
	}
	
	public void quack(){
		duck.quack();
		numberOfQuacks++;
	}
	
	public static int getQuacks(){
		return numberOfQuacks;
	}
	
	/* QuackObservable method, delegating to the duck 
	 * that we're decorating */
	public void registerObserver(Observer observer){
		if(debug){
			System.out.println("Decorator: registering observer..");
		}
		duck.registerObserver(observer);
	}
	
	/* QuackObservable method, delegating to the duck 
	 * that we're decorating */
	public void notifyObservers(){
		if(debug){
			System.out.println("Decorator: notifying observer..");
		}
		duck.notifyObservers();
	}
}
