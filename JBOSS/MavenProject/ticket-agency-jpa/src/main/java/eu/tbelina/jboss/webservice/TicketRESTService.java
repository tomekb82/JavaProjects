package eu.tbelina.jboss.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.tbelina.jboss.model.Seat;
import eu.tbelina.jboss.model.SeatForRest;
import eu.tbelina.jboss.repository.DataManager;

@Path("/seat")
//@RequestScoped
@Stateless
public class TicketRESTService {

	@Inject
	private Logger logger;
	
	@Inject
	private DataManager seatRepository;
	
	@Inject
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SeatForRest> getSeatList() {
		
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

	@POST
	@Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response buyTicket(@PathParam("id") int id) {

		Response.ResponseBuilder builder = null;
		try {	
			Seat seat = seatRepository.findSeatById(Long.valueOf(id));
			seat.setBooked(true);
			em.persist(seat);
			builder = Response.ok("Zarezerwowano bilet");
		}
		catch (Exception e) {
			//Obsługa wyjątków ogólnych.
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();

	}
	
	
}
