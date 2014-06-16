package eu.tbelina.spring.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Host{  

	public static void main(String[] args){  

		ApplicationContext context = new ClassPathXmlApplicationContext("rmi_context.xml");  
	
		System.out.println("Waiting for requests");  
	}  
}  
