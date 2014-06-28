package eu.tbelina.jboss.ejb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import eu.tbelina.jboss.model.Seat;
import static javax.ejb.LockType.*;

/*
 * Ziarno typu Singleton
 * - tworzenie listy miejsc
 */

@Singleton
@Startup
public class TheatreBox {

	private ArrayList<Seat> seatList;
	private static final Logger logger = Logger.getLogger(TheatreBox.class);

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

	@Lock(READ) // wielowątkowy dostęp do ziarna
	public ArrayList<Seat> getSeatList() {

		return seatList;
	}
	
	@Lock(READ) // wielowątkowy dostęp do ziarna
	public int getSeatPrice(int id) {

		return getSeatList().get(id).getPrice();
	}

	@Lock(WRITE) // dostęp do ziarna ma tylko jeden z wątków
	public void buyTicket(int seatId ) {
		Seat seat = getSeatList().get(seatId);

		seat.setBooked(true);
	}
}
