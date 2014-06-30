package eu.tbelina.jboss.webservice;

import java.util.List;

import javax.jws.WebService;

import eu.tbelina.jboss.model.Seat;
import eu.tbelina.jboss.model.SeatForRest;

@WebService
public interface TicketSOAPServiceItf {
  
	public List<SeatForRest> getSeats();

	public void bookSeat(long seatId);
}
