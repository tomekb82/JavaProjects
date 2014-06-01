package com.company.tests;

import com.company.reports.*;

public class RaportTest {

	public static void main(String[] args){
		
		ReportInterface report = new Report();
		report.setName("Raport z walenia młotkiem");
		report.setAuthor("Jan Kowalski");
		report.setDate("2013-05-21");
		report.setVersion("ver 1");
		report.setDevice("DeviceA");
		
		report.printReport();
	}
}
