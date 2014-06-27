package com.packtpub.as7development.chapter4.ejb;

 
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import com.packtpub.as7development.chapter4.model.Seat;
import com.packtpub.as7development.chapter4.service.PollerBean;
 
 

@Stateless
public class AutomaticSellerBean
 {
	 
	@Inject TheatreBox   theatreBox;
	@Inject PollerBean   pollerBean; 
	private final static Logger logger = Logger.getLogger(AutomaticSellerBean.class.getName()); 
	
	 @Resource
	  private TimerService timerService;
	 
	public void cancelTimers() {
		 for (Timer timer : timerService.getTimers()) {
		       
		      timer.cancel();
		    }
	}
    @Schedule(dayOfWeek = "*", hour = "*", minute = "*", second = "*/60",year="*", persistent = false)
     public void backgroundProcessing()
	    {
     	   int seatId = findSeat();
     	  
    	   if (seatId == -1) {
    		   pollerBean.setPollingActive(false);
    		   cancelTimers();
    		   logger.info("Koniec wykonywania harmonogramu!"); 
    		   return ; // Nie ma więcej wolnych miejsc.
    	   }
    	   
    	   
    		   theatreBox.buyTicket(seatId);
	 		 
		   
    	       logger.info("Ktoś właśnie zarezerwował miejsce "+seatId); 
	    }
    public int findSeat() {
    	 ArrayList<Seat> list = theatreBox.getSeatList();
    	for (Seat s: list) {
    		if (!s.isBooked()) {
    			return s.getId()-1;
    		}
    	}
    	 
    	return -1;
    } 
 }
