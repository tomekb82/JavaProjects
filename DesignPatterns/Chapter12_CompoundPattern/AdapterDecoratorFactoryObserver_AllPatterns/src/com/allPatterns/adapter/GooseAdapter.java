package com.allPatterns.adapter;

import com.allPatterns.Quackable;
import com.allPatterns.gooses.*;
import com.allPatterns.ducks.*;

/* Adapter Pattern */
public class GooseAdapter implements Quackable{

	Goose goose;

	public GooseAdapter(Goose goose){
		this.goose = goose;
	}
	
	public void quack(){
		goose.honk();
	}
}
