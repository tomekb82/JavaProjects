package com.packtpub.as7development.chapter8.ejb;

import javax.annotation.PostConstruct;



import static javax.ejb.LockType.*;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;
  
 

import com.packtpub.as7development.chapter8.exception.SeatBookedException;
import com.packtpub.as7development.chapter8.model.Seat;

import java.util.*;
import java.util.logging.Logger;


@Singleton
@Startup
 
public class TheatreBox {

	private ArrayList<Seat> seatList;
	 
 
 
	@PostConstruct
	public void setupTheatre(){
		seatList = new ArrayList<Seat>();
		int seatId = 0;
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(seatId, "Parter",40);
			seatList.add(seat);
			seatId++;
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(seatId,"Amfiteatr",20);
			seatList.add(seat);
			seatId++;
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(seatId, "Balkon",10);
			seatList.add(seat);
			seatId++;
		}
	 

	}

	@Lock(READ)  
	public ArrayList<Seat> getSeatList() {

		return seatList;
	}
	@Lock(READ)  
	public int getSeatPrice(int seatId) {
		return getSeatList().get(seatId).getPrice();
	}

	@Lock(WRITE)  
	public void buyTicket(int seatId )     {
		Seat seat = getSeatList().get(seatId);
        if (seat.isBooked()) {
        	throw new SeatBookedException("Miejsce jest już zarezerwowane!");
        }
		seat.setBooked(true);
        seatEvent.fire(seat);

	}
	@Inject Event<Seat> seatEvent;
}
