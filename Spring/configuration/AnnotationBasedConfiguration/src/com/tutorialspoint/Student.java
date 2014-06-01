package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.*;

public class Student {

	private Integer age;
	private String name;
	private String nick;
	
	@Required
	public void setAge(Integer age) {
	this.age = age;
	}
	public Integer getAge() {
	return age;
	}
	@Required
	public void setName(String name) {
	this.name = name;
	}
	public String getName() {
		return name;
	}
	
	@Autowired(required=false)
	public void setNick(String nick) {
	this.nick = nick;
	}
	public String getNick() {
	return nick;
	}
	
	@PostConstruct
	public void init(){
	System.out.println("Bean Student is going through init.");
	}
	@PreDestroy
	public void destroy(){
	System.out.println("Bean Student will destroy now.");
	}
}
