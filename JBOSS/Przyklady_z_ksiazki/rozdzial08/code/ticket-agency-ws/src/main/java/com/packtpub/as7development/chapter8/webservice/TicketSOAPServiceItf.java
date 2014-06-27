package com.packtpub.as7development.chapter8.webservice;

import java.util.List;

import javax.jws.WebService;

import com.packtpub.as7development.chapter8.model.Seat;

@WebService
public interface TicketSOAPServiceItf {
 	public  List<Seat>  getSeats();
	
	public void bookSeat(int seatId);
}
