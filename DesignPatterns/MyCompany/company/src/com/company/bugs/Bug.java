package com.company.bugs;

import java.util.Iterator;

public class Bug implements BugInterface{

	public String title;
	public String description;
	
	public Bug (String title, String description){
		this.title = title;
		this.description = description;
	}
	
	public void add(String title, String description){
		this.title = title;
		this.description = description;
	}
	
	public void remove(int id);
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void print(){
		System.out.println("    bug: " + getTitle() + ", " + getDescription());
	}
	
}
