package com.packtpub.as7development.chapter8.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

import com.packtpub.as7development.chapter8.model.Seat;
import com.packtpub.as7development.chapter8.webservice.TicketSOAPServiceItf;




public class TicketWebServiceIT {

	@Test
	public void testSOAP() {
		System.out.println("Test usługi sieciowej SOAP");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(TicketSOAPServiceItf.class);
		factory.setAddress("http://localhost:8080/ticket-agency-ws/TicketWebService?wsdl");
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());

		TicketSOAPServiceItf infoService = (TicketSOAPServiceItf) factory.create();
		System.out.println("Otrzymano usługę: "+infoService);
		//BindingProvider bp = (BindingProvider)infoService;

		infoService.bookSeat(1);
		System.out.println("Zarezerwowano bilet przy użyciu JAX-WS");
		
		List<Seat>  list = infoService.getSeats();

		dumpSeatList(list);

		assertTrue(list.size() > 0);

		assertTrue(list.get(1).isBooked());



	}

	private void dumpSeatList(List<Seat> list) {
		System.out.println("================= Lista dostępnych biletów ================");
		for (Seat seat : list)
			System.out.println(seat);
		System.out.println();
	}
	@Test
	public void testREST() {
		System.out.println("Test usługi typu REST");
		
		ClientRequestFactory crf = new ClientRequestFactory(UriBuilder.fromUri(
				"http://localhost:8080/ticket-agency-ws/rest/seat").build());

		 
		ClientRequest bookRequest = crf
				.createRelativeRequest("/4");
		 
		String entity=null;
		try {
			entity = bookRequest.post(String.class).getEntity();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		 
		assertTrue(entity.equals("Zarezerwowano bilet")); 
        System.out.println("Zarezerwowano bilet w usłudze REST");
		ClientRequest request = crf
				.createRelativeRequest("/");
		String seatList=null;
		try {
			seatList = request.get(String.class).getEntity();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Lista miejsc \n" + seatList);
		 
		Object obj=JSONValue.parse(seatList);
		JSONArray array=(JSONArray)obj;	                
		JSONObject seat =(JSONObject)array.get(4);

		
		Boolean isbooked = (Boolean)seat.get("booked");
			 
		assertTrue(isbooked);
		
	 

	}

}
