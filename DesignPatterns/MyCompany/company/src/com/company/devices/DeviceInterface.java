package com.company.devices;

import com.company.documentation.*;
import com.company.bugs.*;
import com.company.bugs.composite.*;

public interface DeviceInterface {

	void createDevice(DeviceInterface device, DocumentationInterface document, BugInterface bug);
	
	boolean createDocument();
	boolean createBug();
	
	void setSystem(String system);
	void setModule(String module);
	void setAlgorithm(String algorithm);
	void setVersion(String version);
	void setBug(BugInterface bug);
	void setBug(BugComposite bug);
	
	String getSystem(int id);
	String getModule(int id);
	String getAlgorithm(int id);
	String getVersion(int id);
	String getBug(int id);
	
	void printDevice();
	
	void setDocument(DocumentationInterface document);
	
}
