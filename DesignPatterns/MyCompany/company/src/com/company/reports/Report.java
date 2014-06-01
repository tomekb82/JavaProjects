package com.company.reports;

import java.util.ArrayList;

public class Report implements ReportInterface {

	String name = "<empty>";
	String author = "<empty>";
	String date = "<empty>";
	String device = "<empty>";
	String version = "<empty>";
	
	public Report(){}
	
	public Report(String name,
			String author,
			String date,
			String device,
			String version){

			setName(name);
			setAuthor(author);
			setDate(date);
			setDevice(device);
			setVersion(version);
	}
	public void createReport(String name,
				String author,
				String date,
				String device,
				String version){
		
		setName(name);
		setAuthor(author);
		setDate(date);
		setDevice(device);
		setVersion(version);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDevice(String device){
		this.device = device;
	}
	
	public String getDevice(){
		return device;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return version;
	}
	
	public void print(){
		//System.out.println("        Raport");
		System.out.println("        - name: "+ getName());
		System.out.println("        - author: "+ getAuthor());
		System.out.println("        - date: "+ getDate());
		System.out.println("        - device: "+ getDevice());
		System.out.println("        - version: "+ getVersion());
	}
	
}
