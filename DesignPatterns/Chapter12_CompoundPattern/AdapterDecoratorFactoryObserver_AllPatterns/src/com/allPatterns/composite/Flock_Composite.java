package com.allPatterns.composite;

import java.util.ArrayList;
import java.util.Iterator;

import com.allPatterns.Quackable;
import com.allPatterns.observer.*;

public class Flock_Composite implements Quackable{
	
	ArrayList quackers = new ArrayList();
	
	public void add(Quackable quacker){
		quackers.add(quacker);
	}
	
	public void quack(){

		Iterator iterator = quackers.iterator();
		
		while(iterator.hasNext()){
			Quackable quacker = (Quackable)iterator.next();
			quacker.quack();
		}
	}
	
	public void registerObserver(Observer observer){
		
		Iterator iterator = quackers.iterator();
		
		while (iterator.hasNext()){
			Quackable quacker = (Quackable)iterator.next();
			quacker.registerObserver(observer);
		}
	}
}
