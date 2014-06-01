package com.allPatterns.factory;

import com.allPatterns.Quackable;
import com.allPatterns.decorator.DuckCounterDecorator;
import com.allPatterns.ducks.*;

public class CountingDecorator_DuckFactory extends AbstractDuckFactory{

	public Quackable createMallardDuck(){
		return new DuckCounterDecorator(new MallardDuck());
	}
	
	public Quackable createRedheadDuck(){
		return new DuckCounterDecorator(new RedheadDuck());
	}
	
	public Quackable createDuckCall(){
		return new DuckCounterDecorator(new DuckCall());
	}
	
	public Quackable createRubberDuck(){
		return new DuckCounterDecorator(new RubberDuck());
	}

}
