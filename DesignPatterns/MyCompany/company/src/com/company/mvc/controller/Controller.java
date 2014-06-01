package com.company.mvc.controller;

import com.company.mvc.model.*;
import com.company.mvc.view.*;
import com.company.devices.*;
import com.company.documentation.DocumentationInterface;
import com.company.bugs.*;
import com.company.reports.*;

public class Controller implements ControllerInterface{

	/* reference to MODEL */
	ModelInterface model;
	/* reference to VIEW */
	View view;
	
	/*
	 * Contructor:
	 *  - creates VIEW
	 *  - passed the MODEL
	 */
	public Controller(ModelInterface model){
		this.model = model;
		view = new View(this,model);
		view.createViewDevice();
		//view.disableStopMenuItem();
		//view.enableStartMenuItem();
		model.initialize();
	}
	
	public void start(){
		System.out.println("Controller: start()");
		//model.on();
		//view.disableStartMenuItem();
		//view.enableStopMenuItem();
	}
	
	public void stop(){
		System.out.println("Controller: stop()");
		//model.off();
		//view.disableStopMenuItem();
		//view.enableStartMenuItem();
	}

	public void addDevice(DeviceInterface device){
		model.addDevice(device);
	}
	
	public void addDocument(DocumentationInterface document){
		model.addDocument(document);
	}
	
	public void addBug(BugInterface bug){
		model.addBug(bug);
	}
	
	public void addReport(ReportInterface report, String name){
		model.addReport(report, name);
	}
}
