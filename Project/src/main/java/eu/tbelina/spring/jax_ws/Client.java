package eu.tbelina.spring.jax_ws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import eu.tbelina.spring.model.Expense;

public class Client {

	
	public static void main(String[] args) throws Exception {
		 
		URL url = new URL("http://localhost:8080/spring/jaxws-spring?wsdl");
	 
	    QName qname = new QName("http://jax_ws.spring.tbelina.eu/","MyWebService" );
	 
	    Service service = Service.create(url, qname);
	        
	    IMyWebService myWebService = service.getPort(IMyWebService.class);
	
	    System.out.println(myWebService.printMessage("Filip").toString());
	 
	    System.out.println("JAX-WS, all expenses:");
	    for(Expense e: myWebService.getExpenses()){
	    	System.out.println(" + " +  e.toString());
	    }
	    
	}
}
