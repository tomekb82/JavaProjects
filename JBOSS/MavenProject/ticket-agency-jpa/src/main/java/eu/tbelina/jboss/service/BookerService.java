package eu.tbelina.jboss.service;





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



@Named
@ConversationScoped
public class BookerService implements Serializable {
	@Inject
	private Logger logger;

	int money;
	@Inject TicketService ticketService;
	
	@Inject JMSService jmsService; // wstrzykniecie serwisu JMS
	
	@PostConstruct
	public void createCustomer() {
		this.money=100;

	}

	public void bookSeat(Long seatId, int price)   {
		FacesContext fc = FacesContext.getCurrentInstance(); 

		if (price  > money) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Za mało środków!", "Rejestracja zakończona sukcesem");
			fc.addMessage(null, m);		
			/* JMS send ERROR message 
			 *  - set HIGH priority in jms header
			 * */
			jmsService.sendMessage("[Komunikat JMS] ERROR: Za mało środków! ","HIGH");
			return;
		}
		logger.info("Rezerwuję miejsce "+seatId);
		ticketService.bookSeat(seatId);
		money = money - price;
        				 
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarezerowano!", "Rezerwacja zakończona sukcesem");
		if (fc != null)
		fc.addMessage(null, m);	
		logger.info("Miejsce zarezerwowane.");

		/* JMS send message 
		 *  - set LOW priority in jms header
		 * */
		jmsService.sendMessage("[Komunikat JMS] Zarezerwowano miejsce "+seatId,"LOW");


	}
	public int getMoney() {
		return money;
	}




}
