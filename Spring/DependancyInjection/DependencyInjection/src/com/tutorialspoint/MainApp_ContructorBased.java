package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import
org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp_ContructorBased {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("Beans_constructor_based.xml");
		
		TextEditor te = (TextEditor) context.getBean("textEditor");
		te.spellCheck();
	}
}
