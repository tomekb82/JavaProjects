package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("Beans.xml");
		
		//ApplicationContext context =
		//new ClassPathXmlApplicationContext("Beans.xml");
		
		Student student = (Student) context.getBean("student");
		System.out.println("Name : " + student.getName() );
		System.out.println("Age : " + student.getAge() );
		System.out.println("Nick : " + student.getNick() );
		
		System.out.println("=============");
		//Profile profile = (Profile) context.getBean("profile");
		//profile.printAge();
		//profile.printName();
		
		context.registerShutdownHook();
	
	}
	
}
