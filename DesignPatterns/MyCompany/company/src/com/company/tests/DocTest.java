package com.company.tests;

import com.company.documentation.*;
import com.company.reports.*;

public class DocTest {

	public static void main(String [] args){
		
		DocumentationInterface document = new Documentation();
				
		document.setType("techniczna");
		document.setName("dokument 1");
		
		ReportInterface report1 = new Report();
		ReportInterface report2 = new Report();
		report1.setName("Raport z walenia młotkiem");
		report1.setAuthor("Jan Kowalski");
		report1.setDate("2013-05-21");
		report1.setVersion("ver 1");
		report1.setDevice("DeviceA");
		report2.setName("Raport rąbania drzewa");

		//ReportInterface reports[] = {report1, report2};
		document.setReport(report1);
		document.setReport(report2);
		
		document.printDocumentation();
		
		
	}
}
