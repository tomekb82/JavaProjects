package com.allPatterns.observer;

/* We need to implement Observable interface to register with QuackObservable */
public class Quacklogist implements Observer{
	
	/* print the Quackable that just 'quacked' */
	public void update(QuackObservable duck){
		System.out.println("Quacklogist: " + duck.getClass().getSimpleName() +" just quacked.");
	}

}
