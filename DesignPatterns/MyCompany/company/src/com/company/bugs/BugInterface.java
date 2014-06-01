package com.company.bugs;

import java.util.Iterator;

public interface BugInterface {

	void add(String title, String description);
	
	String getTitle();
	String getDescription();
	void print();	
}
