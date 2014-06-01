package com.company.mvc.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.bugs.*;
import com.company.bugs.composite.*;
import com.company.devices.*;
import com.company.devices.factory.*;
import com.company.documentation.*;
import com.company.documentation.composite.*;
import com.company.documentation.factory.*;
import com.company.documentation.decorator.*;
import com.company.mvc.controller.*;
import com.company.mvc.observer.*;
import com.company.reports.*;
import com.company.reports.decorator.ReportDecorator;
import com.company.reports.factory.*;

public class Model implements ModelInterface {

	DeviceInterface device;
	DocumentationComposite documents;
	BugComposite bugs;
	
	/* observers */
	ArrayList observers = new ArrayList();
	
	public void initialize(){
		documents = new DocumentationComposite();
		bugs = new BugComposite();
	}
	
	public void addDevice(DeviceInterface dev){
		this.device = dev;
		device.setDocument(documents);
		device.setBug(bugs);
		device.printDevice();
		
		notifyObservers();
	}
	
	public void addDocument(DocumentationInterface document){
		documents.add(document);
		
		notifyObservers();
	}
	
	public void addBug(BugInterface bug){		
		bugs.add(bug);
		
		notifyObservers();
	}
	
	public void addReport(ReportInterface report, String name){
		
		Iterator iterator = documents.get().iterator();
		while(iterator.hasNext()){
			DocumentationInterface document = (DocumentationInterface) iterator.next();
			if(document.getName().equals(name) && document.getType().equals("techniczna")){
				DocumentationDecoratorDK doc = (DocumentationDecoratorDK) document;
				doc.setReport(report);
			}
		}
		
		notifyObservers();
	}
	
	public void registerObserver(Observer o){
		observers.add(o);
	}
	
	public void notifyObservers(){
		for(int i=0; i < observers.size(); i++){
			Observer observer = (Observer)observers.get(i);
			observer.update();
		}
	}
	
	public void removeObserver(Observer o){
		int i = observers.indexOf(o);
		if(i >= 0){
			observers.remove(i);
		}
	}
	
}
