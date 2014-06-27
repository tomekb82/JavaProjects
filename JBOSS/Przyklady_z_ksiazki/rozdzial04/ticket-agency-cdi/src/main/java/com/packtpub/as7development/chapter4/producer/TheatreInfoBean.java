package com.packtpub.as7development.chapter4.producer;


import java.util.List;

import javax.annotation.PostConstruct;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;


import com.packtpub.as7development.chapter4.ejb.TheatreBox;
import com.packtpub.as7development.chapter4.model.Seat;

@Model
public class  TheatreInfoBean   {

	@Inject TheatreBox box;

	private List<Seat> seats;

	@Produces
	@Named
	public List<Seat> getSeats() {

		return seats;
	}

	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
		retrieveAllSeatsOrderedByName();
	}

	@PostConstruct
	public void retrieveAllSeatsOrderedByName() {

		seats = box.getSeatList();

	}


}
