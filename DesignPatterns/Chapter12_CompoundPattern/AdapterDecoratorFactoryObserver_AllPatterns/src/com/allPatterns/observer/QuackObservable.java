package com.allPatterns.observer;


/* Quackable should implement interface if they want to be observed */

public interface QuackObservable {
	
	public void registerObserver(Observer observer);
	
	public void notifyObservers();

}
