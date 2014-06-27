/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.packtpub.as7development.chapter7.service;



import com.packtpub.as7development.chapter7.model.Seat;
import com.packtpub.as7development.chapter7.model.SeatType;
import com.packtpub.as7development.chapter7.repository.DataManager;



import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class TicketService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<SeatType> seatTypeEventSrc;

	@Inject
	private Event<Seat> seatEventSrc;

	@Inject
	private DataManager repository;

	@Inject
	private List <SeatType> seatTypes;

	public void createSeatType(SeatType seat) throws Exception {
		log.info("Rejestracja " + seat.getDescription());
		em.persist(seat);
		seatTypeEventSrc.fire(seat);
	} 

	public void createTheatre(List<SeatType> seatTypes) {

		for (SeatType type: seatTypes) {
			for (int ii=0;ii<type.getQuantity();ii++) {
				Seat seat = new Seat();
				seat.setBooked(false);
				seat.setSeatType(type);
				em.persist(seat);
			}
		}
	}

	public void bookSeat(Long seatId) {
		Seat seat = repository.findSeatById(seatId);
		seat.setBooked(true);
		em.persist(seat);
		seatEventSrc.fire(seat);

	}

	public void doCleanUp() {
		repository.deleteAllData();

	}


}
