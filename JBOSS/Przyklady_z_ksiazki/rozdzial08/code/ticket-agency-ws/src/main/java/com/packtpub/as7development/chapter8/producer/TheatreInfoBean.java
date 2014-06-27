package com.packtpub.as7development.chapter8.producer;


import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.Remote;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;

 


import com.packtpub.as7development.chapter8.ejb.TheatreBox;
import com.packtpub.as7development.chapter8.model.Seat;
 

@Model
public class  TheatreInfoBean    {

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
