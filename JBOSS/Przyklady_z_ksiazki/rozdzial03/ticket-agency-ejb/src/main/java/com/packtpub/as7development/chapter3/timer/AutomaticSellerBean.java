package com.packtpub.as7development.chapter3.timer;


import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Schedule;


import com.packtpub.as7development.chapter3.ejb.TheatreBooker;
import com.packtpub.as7development.chapter3.ejb.TheatreBox;
import com.packtpub.as7development.chapter3.ejb.TheatreInfo;
import com.packtpub.as7development.chapter3.exception.NotEnoughMoneyException;
import com.packtpub.as7development.chapter3.exception.SeatBookedException;
import com.packtpub.as7development.chapter3.model.Seat;

@Stateless 
public class AutomaticSellerBean
{
	private final static Logger logger = Logger.getLogger(AutomaticSellerBean.class.getName()); 
	
	@EJB private TheatreBox   theatreBox;

	@Resource
	private TimerService timerService;

	@Schedule(dayOfWeek = "*", hour = "*", minute = "*", second = "*/60",year="*", persistent = false)
	public void automaticCustomer()
	{
		 int seatId = findSeat();
		 
  	   if (seatId == -1) {
  		    cancelTimers();
    	    logger.info("Koniec wykonywania harmonogramu!"); 
    		return ; // Nie ma więcej wolnych miejsc.
  	   }
  	   
  	   
  		   theatreBox.buyTicket(seatId);
	 		 
		   
  	       logger.info("Ktoś właśnie zarezerwował miejsce "+(seatId +1)); 
	}
	private int findSeat() {
		ArrayList<Seat> list = theatreBox.getSeatList();
		for (Seat s: list) {
			if (!s.isBooked()) {
				return s.getId() -1;
			}
		}
		return -1;
	}
	private void cancelTimers() {
		for (Timer timer : timerService.getTimers()) {

			timer.cancel();
		}
	}
}
