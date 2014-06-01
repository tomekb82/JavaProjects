package com.allPatterns.ducks;

import com.allPatterns.Quackable;
import com.allPatterns.observer.*;

public class MallardDuck_Observable implements Quackable {

	Observable observable;  	/* each Quackable has an Observable instance variable */
	boolean debug = true;
	
	/* creating an Observable and pass it a reference to the MallardDuck object */
	public MallardDuck_Observable(){
		if(debug){
			System.out.println("MallardDuck_Observable: creating observer..");
		}
		observable = new Observable(this);
	}
	
	public void quack(){
		System.out.println("MallardDuck_Observable, Quack");
		notifyObservers();		/* when we quack() we need to let the observers know about it */
	}
	
	/* QuackObservable method, we just delegate to the helper */
	public void registerObserver(Observer observer){
		if(debug){
			System.out.println("MallardDuck_Observable: registering observer..");
		}
		observable.registerObserver(observer);
	}
	
	/* QuackObservable method, we just delegate to the helper */
	public void notifyObservers(){
		if(debug){
			System.out.println("MallardDuck_Observable: notifying observer..");
		}
		observable.notifyObservers();
	}

//	public void setDebug(boolean debug){
//		this.debug = debug;
//	}
}
