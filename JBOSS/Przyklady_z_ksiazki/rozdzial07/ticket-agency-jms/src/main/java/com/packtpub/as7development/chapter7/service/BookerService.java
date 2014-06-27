package com.packtpub.as7development.chapter7.service;





import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.Logger;


import com.packtpub.as7development.chapter7.model.Seat;
import com.packtpub.as7development.chapter7.service.TicketService;



@Named
@ConversationScoped
public class BookerService implements Serializable {
	@Inject
	private Logger logger;

	int money;
	@Inject TicketService ticketService;
	
	@Inject JMSService jmsService;

	@PostConstruct
	public void createCustomer() {
		this.money=100;

	}



	public void bookSeat(Long seatId, int price)   {
		FacesContext fc = FacesContext.getCurrentInstance(); 

		if (price  > money) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Za mało środków!", "Rejestracja zakończona sukcesem");
			fc.addMessage(null, m);		  
			return;
		}
		logger.info("Rezerwuję miejsce "+seatId);
		ticketService.bookSeat(seatId);
		money = money - price;
        				 
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarezerowano!", "Rezerwacja zakończona sukcesem");
		if (fc != null)
		fc.addMessage(null, m);	
		
		jmsService.sendMessage("[Komunikat JMS] Zarezerwowano miejsce "+seatId,"HIGH");


	}
	public int getMoney() {
		return money;
	}




}
