package com.company.documentation;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.reports.ReportInterface;

public class DocumentationOFU implements DocumentationInterface {

	String type;
	String name;
	
	public DocumentationOFU(String name){
		this.type = "opis funkcjonalny";
		this.name = name;
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
	
	public void setReport(ReportInterface report){}
	
	public ReportInterface getReport(int id){}
	
	public void print(){
		System.out.println("    Document OFU:");
		System.out.println("    - type: "+ getType());
		System.out.println("    - name: "+ getName());
		
	}

}