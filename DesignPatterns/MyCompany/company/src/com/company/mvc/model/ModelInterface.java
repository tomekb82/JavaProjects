package com.company.mvc.model;

import com.company.mvc.controller.ReportInterface;
import com.company.mvc.observer.*;
import com.company.devices.*;
import com.company.documentation.DocumentationInterface;
import com.company.bugs.*;
import com.company.reports.*;

public interface ModelInterface {

	void initialize();
	
	/* Controller methods */
	void addDevice(DeviceInterface device);
	void addDocument(DocumentationInterface document);
	void addBug(BugInterface bug);
	void addReport(ReportInterface report, String name);
	
	void registerObserver(Observer o);
	void removeObserver(Observer o);
}
