package com.allPatterns;

import com.allPatterns.observer.*;;

/* We make all Quackables implement QuackObservable interface */

public interface Quackable extends QuackObservable{
	
	public void quack();
	
}
