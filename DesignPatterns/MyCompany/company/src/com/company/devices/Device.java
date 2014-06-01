package com.company.devices;

import java.util.ArrayList;
import java.util.Iterator;
import com.company.documentation.*;
import com.company.bugs.*;
import com.company.bugs.composite.*;

public class Device implements DeviceInterface{

	ArrayList systems;
	ArrayList modules;
	ArrayList algorithms;
	ArrayList versions;
	ArrayList bugs;
	ArrayList documents;

	public boolean createDocument(){
		return true;
	}
	
	public boolean createBug(){
		return true;
	}
	
	public void createDevice(DeviceInterface device, DocumentationInterface document, BugInterface bug){
		setSystem(device.getSystem(0));
		setModule(device.getModule(0));
		setAlgorithm(device.getAlgorithm(0));
		setVersion(device.getVersion(0));
		if(createDocument()){
			if(document != null)
				setDocument(document);
		}
		if(createBug()){
			if(bug != null)
				setBug(bug);
		}
	}
	
	public Device(){
		systems = new ArrayList();
		modules = new ArrayList();
		algorithms = new ArrayList();
		versions = new ArrayList();
		bugs = new ArrayList();
		documents = new ArrayList();
	}
	
	public void setDocument(DocumentationInterface document){
		documents.add(document);
	}
	
	public void setSystem(String system){
		systems.add(system);
	}
	
	public void setModule(String module){
		modules.add(module);
	}
	
	public void setAlgorithm(String algorithm){
		algorithms.add(algorithm);
	}
	
	public void setVersion(String version){
		versions.add(version);
	}
	
	public void setBug(BugInterface bug){
		bugs.add(bug);
	}
	
	public void setBug(BugComposite bug){
		bugs.add(bug);
	}
	
	public String getSystem(int id){
		return (String)systems.get(id);
	}
	
	public String getModule(int id){
		return (String)modules.get(id);
	}
	
	public String getAlgorithm(int id){
		return (String)algorithms.get(id);
	}
	
	public String getVersion(int id){
		return (String)versions.get(id);
	}
	
	public BugInterface getBug(int id){
		return (BugInterface)bugs.get(id);
	}
	
	public void printDevice(){
		//System.out.println("****************** DEVICE  ********************\n");
		printDevice("- System", systems.iterator());
		printDevice("- Modules", modules.iterator());
		printDevice("- Algorithms", algorithms.iterator());
		printDevice("- Versions", versions.iterator());
		printBug("- Bugs", bugs.iterator());
		printDocumentation("- Documentation", documents.iterator());
		System.out.println("\n***********************************************");
	}
	
	public void printDevice(String name, Iterator iterator){
		System.out.print(name + ": ");
		while(iterator.hasNext()){
			System.out.print(iterator.next() + " ");
		}
		System.out.println("\n===============================================");
	}
	
	public void printBug(String name, Iterator iterator){
		System.out.println(name + ":\n");
		while(iterator.hasNext()){
			BugInterface b = (BugInterface)iterator.next();
			b.print();
		}
		System.out.println("===============================================");
	}
	
	
	public void printDocumentation(String name, Iterator iterator){
		System.out.println(name + ":\n");
		while(iterator.hasNext()){
			DocumentationInterface document = (DocumentationInterface)iterator.next();
			document.print();
		}
		System.out.println("===============================================");
	}
}
