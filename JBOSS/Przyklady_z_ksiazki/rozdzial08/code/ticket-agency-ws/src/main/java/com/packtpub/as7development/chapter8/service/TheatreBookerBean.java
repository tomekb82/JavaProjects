package com.packtpub.as7development.chapter8.service;





import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;

 
import com.packtpub.as7development.chapter8.ejb.TheatreBox;
import com.packtpub.as7development.chapter8.model.Seat;


@Named
@SessionScoped
public class TheatreBookerBean implements Serializable  {
 

	int money;
	@Inject TheatreBox theatreBox;

	@PostConstruct
	public void createCustomer() {
		this.money=100;
	}

	
 
	public void bookSeat(int seatId)   {
		FacesContext fc = FacesContext.getCurrentInstance(); 


		System.out.println("Rezerwacja miejsca "+seatId);
		Seat seat = theatreBox.getSeatList().get(seatId);

		if (seat.getPrice() > money) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Za mało środków!", "Rejestracja zakończona sukcesem");
			fc.addMessage(null, m);		 
			return;
		}

		theatreBox.buyTicket(seatId);

		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarezerowano!", "Rezerwacja zakończona sukcesem");
		fc.addMessage(null, m);	
		System.out.println("Miejsce zarezerwowane.");

		money = money - seat.getPrice();


	}
	public int getMoney() {
		return money;
	}




}
