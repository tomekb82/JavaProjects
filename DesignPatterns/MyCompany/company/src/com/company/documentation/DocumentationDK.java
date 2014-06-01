package com.company.documentation;

import java.util.ArrayList;

import java.util.Iterator;
import com.company.reports.*;
import com.company.reports.decorator.ReportDecorator;

public class DocumentationDK implements DocumentationInterface {

	String type;
	String name;
	ArrayList reports;
	
	public DocumentationDK(String name){
		this.type = "techniczna";
		this.name = name;
		reports = new ArrayList();
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setReport(ReportInterface report){
		reports.add(report);
	}
	
	public ReportInterface getReport(int id){
		return reports.get(id);
	}
	
	public void print(){
		int poz = 1;
		System.out.println("    Document DK:");
		System.out.println("    - type: "+ getType());
		System.out.println("    - name: "+ getName());
		System.out.println("    - reports: ");
		
		System.out.println("	Number of raports: " + ReportDecorator.getNumReports());
		Iterator iterator = reports.iterator();
		while(iterator.hasNext()){
			ReportInterface report = (ReportInterface) iterator.next();
			System.out.println("        -------------------------------------");
			System.out.println("        Raport(" + poz +"): ");
			report.print();
			poz+=1;
		}
		System.out.println("    ________________________________________\n");
	}
}
