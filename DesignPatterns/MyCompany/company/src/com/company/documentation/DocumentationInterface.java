package com.company.documentation;

import com.company.reports.*;

public interface DocumentationInterface {

	void setType(String type);
	void setName(String name);
	void setReport(ReportInterface report);
	
	String getType();
	String getName();
	ReportInterface getReport(int id);
	
	void print();
	
}
