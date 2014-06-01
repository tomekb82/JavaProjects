package com.company.tests;

import com.company.bugs.BugInterface;
import com.company.devices.*;
import com.company.devices.factory.*;
import com.company.devices.composite.*;
import com.company.documentation.*;
import com.company.documentation.factory.*;
import com.company.documentation.composite.*;
import com.company.reports.*;
import com.company.reports.decorator.*;
import com.company.reports.factory.*;
import com.company.bugs.*;
import com.company.bugs.composite.*;

public class DeviceTest {

	public static void main(String[] args){
		
		/* FACTORY */
		AbstractDeviceFactory deviceFactory = new DeviceFactory();
		DeviceInterface deviceA = deviceFactory.createDeviceA();
		DeviceInterface deviceB = deviceFactory.createDeviceA();
		DeviceInterface deviceC = deviceFactory.createDeviceA();
		DeviceInterface deviceD = deviceFactory.createDeviceA();
		/* END FACTORY */
		
		/* COMPOSITE */
		DeviceComposite deviceComposite = new DeviceComposite();
		DeviceInterface deviceA1 = deviceFactory.createDeviceA();
		DeviceInterface deviceA2 = deviceFactory.createDeviceA();
		DeviceInterface deviceA3 = deviceFactory.createDeviceA();
		deviceComposite.add(deviceA1);
		deviceComposite.add(deviceA2);
		deviceComposite.add(deviceA3);
		//System.out.println("\n=========== COMPOSITE  ==============");
		//deviceComposite.setSystem("Composite System 1");
		//deviceComposite.setSystem("Composite System 2");
		//deviceComposite.printDevice();
		//System.out.println("\n========= END COMPOSITE ==============");
		/* END COMPOSITE */
		
		//deviceA.createDocument() = false;
		//deviceA.createBug() = false
		
		BugInterface bug1 = new Bug();
		BugInterface bug2 = new Bug();
		BugInterface bug3 = new Bug();
		BugInterface bug4 = new Bug();
		BugComposite bugs = new BugComposite();
		bug1.add("bug1", "pierwszy błąd");
		bug2.add("bug2", "drugi błąd");
		bug3.add("bug3", "trzeci błąd");
		bug4.add("bug4", "czwarty błąd");
		
		bugs.add(bug1);
		bugs.add(bug2);
		bugs.add(bug3);
		
		//deviceA.createDevice("System 1", "Module 1", "alg1", "ver1", null, bugs);
			
		/* DOKUMENTACJA */
		AbstractDocumentationFactory documentFactory = new DocumentationFactory();
		DocumentationComposite documents = new DocumentationComposite();
		DocumentationInterface documentDK = documentFactory.createDocDK("dokument 1");
		DocumentationInterface documentOFU = documentFactory.createDocOFU("dokument 2");
		
		/* RAPORT */
		AbstractReportFactory reportFactory = new ReportFactory();
		ReportInterface report1 = reportFactory.createReport();  
		ReportInterface report2 = reportFactory.createReport();  
		report1.createReport(
				"Raport z walenia młotkiem", 
				"Jan Kowalski", 
				"2013-05-21", 
				"DeviceA", 
				"ver 1");
		report2.createReport(
				"Raport mycia szyb", 
				"Jan Nowak", 
				"2012-05-21", 
				"DeviceB", 
				"ver 2");
		/* END RAPORT */
		documentDK.setReport(report1);
		documentDK.setReport(report2);
		
		documents.add(documentDK);
		documents.add(documentOFU);
		
		deviceA.createDevice("System 1", "Module 1", "alg1", "ver1", documents, bugs);
		deviceA.printDevice();
		/* END DOKUMENTACJA */
	
		
		
		
		
		deviceB.setSystem("System 1");
		deviceC.setVersion("Ver 1");
		deviceD.setModule("Module 1");
		
		//deviceA.printDevice();
		//deviceB.printDevice();
		//deviceC.printDevice();
		//deviceD.printDevice();
		
		/* FUNKCJE GET */
		//System.out.println("System(3): " + deviceA.getSystem(3));
		//System.out.println("Module(2): " + deviceA.getModule(1));
		//System.out.println("Alg(1): " + deviceA.getAlgorithm(0));
		//System.out.println("Ver(1): " + deviceA.getVersion(0));
		//System.out.println("Bug(2): " + deviceA.getBug(1));
		/* END FUNKCJE GET */	
	}
}
