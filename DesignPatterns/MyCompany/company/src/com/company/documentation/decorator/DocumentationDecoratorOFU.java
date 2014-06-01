package com.company.documentation.decorator;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.documentation.Documentation;
import com.company.documentation.DocumentationInterface;
import com.company.reports.ReportInterface;
import com.company.reports.decorator.ReportDecorator;

public class DocumentationDecoratorOFU extends Documentation{

	String type = "opis funkcjonalny";
	DocumentationInterface documentation;

	public DocumentationDecoratorOFU(DocumentationInterface document){
		super(document.getName());
		this.documentation = document;
	}
	
	public String getType(){
		return type;
	}
		
	public void print(){
		System.out.println("    Document OFU:");
		System.out.println("    - type: "+ getType());
		System.out.println("    - name: "+ getName());	
		System.out.println("    ________________________________________\n");
	}
}

