package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class MainApp_hello_world {

	public static void main(String[] args) {
		ApplicationContext ctx =
		new AnnotationConfigApplicationContext(HelloWorldConfig.class);
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.setMessage("Hello World!");
		helloWorld.getMessage();
		
		HelloWorld helloWorld2 = ctx.getBean(HelloWorld.class);
		helloWorld2.getMessage();
		}
	
}
