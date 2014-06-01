package com.company.documentation.decorator;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.documentation.*;
import com.company.reports.ReportInterface;
import com.company.reports.decorator.ReportDecorator;

public class DocumentationDecoratorDK extends Documentation{

	String type = "techniczna";
	int numberOfReports = 0;
	ArrayList reports = new ArrayList();
	DocumentationInterface documentation;

	public DocumentationDecoratorDK(DocumentationInterface document){
		super(document.getName());
		this.documentation = document;
	}
	
	public String getType(){
		return type;
	}
	
	public void updateNumOfReports(){
		numberOfReports++;
	}
	
	public int getNumReports(){
		return numberOfReports;
	}
	
	public void setReport(ReportInterface report){
		reports.add(report);
		updateNumOfReports();
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
		
		System.out.println("	Number of raports: " + getNumReports());
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
