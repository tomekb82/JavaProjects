package com.composite;

import java.util.*;

public abstract class MenuComponent {

	/* Composite Methods */
	public void add(MenuComponent menuComponent){
		throw new UnsupportedoperationException();
	}
	public void remove(MenuComponent menuComponent){
		throw new UnsupportedoperationException();
	}
	public MenuComponent getChild(int i){
		throw new UnsupportedoperationException();
	}
	
	/* Operation Methods */
	public String getName(){
		throw new UnsupportedoperationException();
	}
	public String getDescription(){
		throw new UnsupportedoperationException();
	}
	public double getPrice(){
		throw new UnsupportedoperationException();
	}
	public boolean isVegetarian(){
		throw new UnsupportedOperationException();
	}
	
	public Iterator createIterator();
	
	public void print(){
		throw new UnsupportedoperationException();
	}
}
