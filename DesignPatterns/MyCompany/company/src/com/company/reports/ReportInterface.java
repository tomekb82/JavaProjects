package com.company.reports;

public interface ReportInterface {

	void createReport(String name,
				String author,
				String date,
				String device,
				String version);
	
	void setName(String name);
	void setAuthor(String author);
	void setDate(String date);
	void setDevice(String device);
	void setVersion(String version);
	
	String getName();
	String getAuthor();
	String getDate();
	String getDevice();
	String getVersion();
	
	void print();
}
