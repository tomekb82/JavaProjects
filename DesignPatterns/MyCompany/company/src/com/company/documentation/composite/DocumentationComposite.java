package com.company.documentation.composite;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.bugs.BugInterface;
import com.company.documentation.DocumentationInterface;

public class DocumentationComposite implements DocumentationInterface{

	ArrayList documentations = new ArrayList();

	public void add(DocumentationInterface document){
		documentations.add(document);
		
	}

	public ArrayList get(){
		return documentations;
	}
	
	public void remove(int id){
		documentations.remove(id);
	}
	
	public void print(){
		Iterator iterator = documentations.iterator();
		while(iterator.hasNext()){
			DocumentationInterface document = (DocumentationInterface)iterator.next();
			document.print();
		}
	}
}
