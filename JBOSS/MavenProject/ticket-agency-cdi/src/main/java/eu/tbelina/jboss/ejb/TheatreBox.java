package eu.tbelina.jboss.ejb;

import javax.annotation.PostConstruct;

import static javax.ejb.LockType.*;

import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;
  

import org.jboss.logging.Logger;



import eu.tbelina.jboss.model.Seat;

import java.util.*;


@Singleton
@Startup
 
public class TheatreBox {

	private ArrayList<Seat> seatList;
	private static final Logger logger =
			Logger.getLogger(TheatreBox.class);
 
 
	@PostConstruct
	public void setupTheatre(){
		seatList = new ArrayList<Seat>();
		int id = 0;
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id, "Parter",40);
			seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id, "Balkon I",20);
			seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id, "Balkon II",10);
			seatList.add(seat);
		}
		logger.info("Utworzono listę miejsc.");

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

		seat.setBooked(true);
        seatEvent.fire(seat);

	}
	@Inject Event<Seat> seatEvent;
}
