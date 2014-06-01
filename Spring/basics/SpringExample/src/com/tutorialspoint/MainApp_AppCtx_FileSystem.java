package com.tutorialspoint;
import org.springframework.context.ApplicationContext;
import
org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp_AppCtx_FileSystem {
	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("../HelloSpring/src/Beans.xml");
		
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		}
}
