package eu.tbelina.jboss.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import eu.tbelina.jboss.model.Seat;

/* 
 * Ziarno bezstanowe
 *  - przygotowanie listy miejsc na podstawie zawartości tablicy (fasada)
 */
@Stateless
@Remote(TheatreInfo.class)  // wywoływanie komponentu EJB z poziomu klienta zdalnego
public class TheatreInfoBean implements TheatreInfo{
	
	@EJB TheatreBox box; // wstrzyknięcie zależnosci

	@Override
	public String printSeatList() {
		ArrayList<Seat> seats= box.getSeatList();
		StringBuffer sb = new StringBuffer();
		for (Seat seat: seats) {
			sb.append(seat.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
