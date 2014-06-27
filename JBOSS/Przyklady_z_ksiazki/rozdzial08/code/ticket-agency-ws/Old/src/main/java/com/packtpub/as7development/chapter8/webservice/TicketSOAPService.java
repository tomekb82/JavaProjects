package com.packtpub.as7development.chapter8.webservice;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.jws.*;

import com.packtpub.as7development.chapter8.ejb.TheatreBox;
import com.packtpub.as7development.chapter8.exception.SeatBookedException;
import com.packtpub.as7development.chapter8.model.Seat;
import com.packtpub.as7development.chapter8.producer.TheatreInfoBean;

@WebService(targetNamespace = "http://www.packtpub.com/", serviceName = "TicketWebService") 
public class TicketSOAPService implements TicketSOAPServiceItf, Serializable {
	@Inject TheatreBox theatreBox;
	@Inject TheatreInfoBean infoBean;


	@WebMethod
	@WebResult(name="listSeats")   
	public  List<Seat>  getSeats() {
        
		 return infoBean.getSeats();
	}

	@WebMethod
	public void bookSeat(@WebParam(name="seatId") int seatId) {
		try {
			theatreBox.buyTicket(seatId);
		} catch (SeatBookedException e) {
			e.printStackTrace();
			throw e;
		}

	}
}
