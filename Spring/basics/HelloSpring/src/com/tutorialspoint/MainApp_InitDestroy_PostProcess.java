package com.tutorialspoint;

import org.springframework.context.support.AbstractApplicationContext;
import
org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp_InitDestroy_PostProcess {

	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("Beans_init_destroy.xml");
		
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		/* Here you need to register a shutdown	hookregisterShutdownHook() method 
		 * that is declared on the AbstractApplicationContext class.
		This will ensures a graceful shutdown and calls the relevant destroy methods.
		 */
		context.registerShutdownHook();
		}
	
}
