package eu.tbelina.jboss.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;

import eu.tbelina.jboss.exception.NotEnoughMoneyException;
import eu.tbelina.jboss.exception.SeatBookedException;

public interface TheatreBooker {

	public String bookSeat(int seatId) throws SeatBookedException,NotEnoughMoneyException;
	
	@Asynchronous
	public Future<String> bookSeatAsync(int seatId);
}
