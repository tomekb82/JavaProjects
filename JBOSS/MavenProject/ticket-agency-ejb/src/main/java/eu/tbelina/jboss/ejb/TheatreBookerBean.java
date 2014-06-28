package eu.tbelina.jboss.ejb;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.EJB;

import org.jboss.logging.Logger;

import eu.tbelina.jboss.exception.NotEnoughMoneyException;
import eu.tbelina.jboss.exception.SeatBookedException;
import eu.tbelina.jboss.model.Seat;

public class TheatreBookerBean implements TheatreBooker{

	private static final Logger logger = Logger.getLogger(TheatreBookerBean.class);

	int money;
	@EJB TheatreBox theatreBox; // wstrzykniecie zaleznosci
	
	@PostConstruct  // inicjalizacja zmiennej sesyjnej
	public void createCustomer() {
		this.money=100;
	}

	@Override
	public String bookSeat(int seatId) throws SeatBookedException,
			NotEnoughMoneyException {
		
		Seat seat = theatreBox.getSeatList().get(seatId);
		if (seat.isBooked()) {
			throw new SeatBookedException("To miejsce jest już zarezerwowane!"); // wyjątek sprawdzany
		}
		if (seat.getPrice() > money) {
			throw new NotEnoughMoneyException("Nie masz wystarczających środków, by kupić ten bilet!"); // wyjątek wykonania
		}
		theatreBox.buyTicket(seatId);
		money = money -seat.getPrice();
		logger.info("Rezerwacja przyjęta.");
		return "Rezerwacja przyjęta.";
		
	}

	@Override
	public Future<String> bookSeatAsync(int seatId) {
		Seat seat = theatreBox.getSeatList().get(seatId);
		if (seat.isBooked()) {
			return new AsyncResult<String>("Miejsce "+seatId+" jest już zarezerwowane!"); // wyjątek sprawdzany
		}
		if (seat.getPrice() > money) {
			return new AsyncResult<String>("Nie masz wystarczających środków, by kupić ten bilet!"); // wyjątek wykonania
		}
		try {
			Thread.sleep(10000); // symulacja opoznienia
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("Rezerwacja przyjęta.");
		theatreBox.buyTicket(seatId);
		money = money -seat.getPrice();

		return new AsyncResult<String>("Zarezerowane miejsce: "+seat+" - Pozostało pieniędzy: "+money);
	}

}
