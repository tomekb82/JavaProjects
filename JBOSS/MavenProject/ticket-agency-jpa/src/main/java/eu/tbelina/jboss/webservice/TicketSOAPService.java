package eu.tbelina.jboss.webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import eu.tbelina.jboss.model.Seat;
import eu.tbelina.jboss.model.SeatForRest;
import eu.tbelina.jboss.repository.DataManager;

@Stateless
@WebService(targetNamespace = "http://www.packtpub.com/", serviceName = "TicketWebService") 
public class TicketSOAPService implements TicketSOAPServiceItf, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@Inject
	private DataManager seatRepository;
	
	@Inject
	private EntityManager em;
	
	@Override
	@WebMethod
	@WebResult(name="listSeats") 
	public List<SeatForRest> getSeats() {
		
		List<SeatForRest> srList = new ArrayList<SeatForRest>();
		
		logger.info("REST, POBIERAM LISTE: ");
		for(Seat s : seatRepository.findAllSeats()){
			SeatForRest sr = new SeatForRest();
			sr.setId(s.getId().intValue());
			sr.setBooked(s.getBooked());
			sr.setSeatName(s.getSeatType().getDescription());
			sr.setPrice(s.getSeatType().getPrice());
			logger.info("- " + sr.toString());
			srList.add(sr);
		}
		
		return srList;
	}

	@Override
	@WebMethod
	public void bookSeat(@WebParam(name="seatId")long seatId) {
		Seat seat = seatRepository.findSeatById(seatId);
		seat.setBooked(true);
		em.persist(seat);
		
	}

}
