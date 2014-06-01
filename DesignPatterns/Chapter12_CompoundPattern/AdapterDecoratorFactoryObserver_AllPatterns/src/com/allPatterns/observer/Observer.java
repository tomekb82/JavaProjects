package com.allPatterns.observer;

/* Observer side: */
public interface Observer {

	/* update() method just passed the QuackObservable that is 'quacking' */
	public void update(QuackObservable duck);
}
