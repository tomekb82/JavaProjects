package com.company.documentation;

import java.util.ArrayList;
import java.util.Iterator;
import com.company.reports.*;

public class Documentation implements DocumentationInterface{ 

	String type;
	String name;
	
	public Documentation(){}
	
	public Documentation(String name){
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
		System.out.println("    Document:");
		System.out.println("    - type: "+ getType());
		System.out.println("    - name: "+ getName());
	}
}
