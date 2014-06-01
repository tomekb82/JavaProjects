package com.company.reports.decorator;

import com.company.reports.*;

public class ReportDecorator extends Report {

	ReportInterface report;
	boolean actuality = true;
	
	public ReportDecorator(ReportInterface report){
		this.report = report;	
	}
	
	public void createReport(String name,
			String author,
			String date,
			String device,
			String version){
		report.createReport(name, author, date, device, version);
	}
	
	public boolean getActuality(){
		return actuality;
	}
	
	public void setActuality(boolean actuality){
		this.actuality = actuality;
	}
	
	public void print(){
		report.print();
		System.out.println("        - actuality: ("+ getActuality() +")");
	}
}
