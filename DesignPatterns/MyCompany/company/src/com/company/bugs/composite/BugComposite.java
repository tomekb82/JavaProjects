package com.company.bugs.composite;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.bugs.*;

public class BugComposite implements BugInterface{

	ArrayList bugs = new ArrayList();
	
	public void add(BugInterface bug){
		bugs.add(bug);
		
	}
	
	public void remove(int id){
		bugs.remove(id);
	}
	
	public void print(){
		Iterator iterator = bugs.iterator();
		while(iterator.hasNext()){
			BugInterface bug = (BugInterface)iterator.next();
			bug.print();
		}
	}
}
