package com.tutorialspoint_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext context =
		new ClassPathXmlApplicationContext("Beans_xml.xml");
		//new ClassPathXmlApplicationContext("Beans_xml_2.xml");
		
		Student student = (Student) context.getBean("student");
		student.getName();
		student.getAge();
		//student.printThrowException();
		}
}
