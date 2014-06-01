package com.proxypattern.person_bean;

public interface PersonBean {

	String getName();
	String getGender();
	String getInterests();
	int getHotOrNotRating();
	
	void setName(String name);
	void setGender(String Gender);
	void setInterests(String interests );
	void setHotOrNotRating(int rating);
}
