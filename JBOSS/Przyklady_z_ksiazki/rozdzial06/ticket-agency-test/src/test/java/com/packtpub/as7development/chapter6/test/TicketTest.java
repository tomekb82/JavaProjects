package com.packtpub.as7development.chapter6.test;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.packtpub.as7development.chapter6.model.*;
import com.packtpub.as7development.chapter6.producer.*;
import com.packtpub.as7development.chapter6.service.*;
import com.packtpub.as7development.chapter6.repository.*;
import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.MutableBoundRequest;

@RunWith(Arquillian.class)
public class TicketTest {

	@Inject BoundConversationContext conversationContext;

	@Before
	public void init() {
		conversationContext.associate(
				new MutableBoundRequest(new HashMap<String, Object>(),
						new HashMap<String, Object>()));
		conversationContext.activate();
	}
	@Deployment
	public static Archive<?> createTestArchive() {


		return ShrinkWrap.create(WebArchive.class, "ticket-agency-test.war")
				.addPackage(SeatProducer.class.getPackage())
				.addPackage(Seat.class.getPackage())
				.addPackage(TicketService.class.getPackage())
				.addPackage(DataManager.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				;


	}

	@Inject
	TicketService ticketService;

	@Inject
	BookerService bookerService;

	@Inject
	Logger log;

	@Test
	public void testTicketAgency() throws Exception {

		SeatType seatType = new SeatType();
		seatType.setDescription("Balkon");
		seatType.setPrice(11);
		seatType.setQuantity(5);

		ticketService.createSeatType(seatType);
		log.info("Utworzono typ miejsc "+seatType.getDescription());
		assertNotNull(seatType.getId());

		List listSeats = new ArrayList();
		listSeats.add(seatType);
		ticketService.createTheatre(listSeats);

		log.info("Utworzono teatr");

		log.info(seatType.getDescription() + " zostało utrwalone z identyfikatorem " + seatType.getId());

		bookerService.bookSeat(new Long(seatType.getId()), seatType.getPrice());
		log.info("Dokonano rezerwacji");
		log.info("Pozostała kwota: " +bookerService.getMoney());
		assertTrue(bookerService.getMoney() <100);
	}

}
