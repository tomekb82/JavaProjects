package com.company.mvc.controller;

import com.company.devices.*;
import com.company.bugs.*;
import com.company.documentation.*;
import com.company.reports.*;

public interface ControllerInterface {

	/*
	 * All methods the VIEW can call on the CONTROLLER
	 */
	void addDevice(DeviceInterface device);
	void addDocument(DocumentationInterface document);
	void addBug(BugInterface bug);
	void addReport(ReportInterface report, String name);
}
