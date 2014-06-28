package eu.tbelina.jboss.timer;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import eu.tbelina.jboss.ejb.TheatreBox;
import eu.tbelina.jboss.model.Seat;

@Stateless 
public class AutomaticSellerBean {

	private final static Logger logger = Logger.getLogger(AutomaticSellerBean.class.getName());

	@EJB // wstrzykniecie zaleznosci
	private TheatreBox theatreBox;

	@Resource
	private TimerService timerService;
	
	private static int add = 0;
	long duration = 12000; // in ms
	
	public void createTimer(){
		timerService.createSingleActionTimer(duration, new TimerConfig());
	}
	
	@Timeout// wykorzystanie pojedynczego czasomierza akcji
	public void timeout(Timer timer){
		logger.info("Przebudowanie listy miejsc (pojedynczy czasomierze EJB");
	}
	
	//@Schedule(dayOfWeek = "*", hour = "*", minute = "*", second = "*/60",year="*", persistent = false)
	public void automaticCustomer(){
		
		if(add==0){
			createTimer();
			add = 1;
		}
		
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
