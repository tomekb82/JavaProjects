package eu.tbelina.spring.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {  

	public static void main(String[] args)  {  

		ApplicationContext context = new ClassPathXmlApplicationContext("rmi_client.xml");  

		Calculation calculation = (Calculation)context.getBean("calculationBean");  

		System.out.println(calculation.cube(7));  

	}  
}  
