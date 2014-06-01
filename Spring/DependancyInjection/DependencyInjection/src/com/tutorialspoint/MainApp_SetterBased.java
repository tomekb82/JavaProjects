package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import
org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp_SetterBased {
	
	public static void main(String[] args) {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("Beans_setter_based.xml");
		
		TextEditor_SetterBased te = (TextEditor_SetterBased) context.getBean("textEditor");
		te.spellCheck();
		
	}
}
