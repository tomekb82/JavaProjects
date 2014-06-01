package com.allPatterns.observer;

import java.util.ArrayList;
import java.util.Iterator;
import com.allPatterns.observer.Observer;
import com.allPatterns.observer.QuackObservable;

/* Observable implements all functionality a Quackable needs to be an observable */

public class Observable implements QuackObservable{
	
	ArrayList observers = new ArrayList();
	QuackObservable duck;
	boolean debug = false;
	
	public Observable(QuackObservable duck){
		if(debug){
			System.out.println("Observable: creating object..");
		}
		this.duck = duck;
	}
	
	/* Registering observer */
	public void registerObserver(Observer observer){
		if(debug){
			System.out.println("Observable: registering observer..");
		}
		observers.add(observer);
	}

	public void notifyObservers(){
		if(debug){
			System.out.println("Observable: notifying observers..");
		}
		Iterator iterator = observers.iterator();
		while(iterator.hasNext()){
			Observer observer = (Observer)iterator.next();
			observer.update(duck);	
		}
	}
	
	public void setDebug(boolean debug){
		this.debug = debug;
	}
}
