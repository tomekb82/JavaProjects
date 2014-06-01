package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import
org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp_BeansTemplate {

	public static void main(String[] args) {
		ApplicationContext context =
				//new ClassPathXmlApplicationContext("Beans.xml");
			    new ClassPathXmlApplicationContext("Beans_template.xml");
		
		//HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
		//objA.getMessage1();
		//objA.getMessage2();
		
		HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
		objB.getMessage1();
		objB.getMessage2();
		objB.getMessage3();
		
	}
}
