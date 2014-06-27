package com.packtpub.as7development.chapter10.client;

import javax.naming.*;

/*import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClient;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.StatelessEJBLocator;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;*/


import com.packtpub.as7development.chapter10.ejb.TheatreBooker;
import com.packtpub.as7development.chapter10.ejb.TheatreInfo;
import com.packtpub.as7development.chapter10.exception.NotEnoughMoneyException;
import com.packtpub.as7development.chapter10.exception.SeatBookedException;
import com.packtpub.as7development.chapter10.utils.IOUtils;
 
 
 

import java.util.*;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RemoteEJBClient {
	private final static Logger logger = Logger.getLogger(RemoteEJBClient.class .getName()); 
	private final static Hashtable jndiProperties = new Hashtable();
	
	public static void main(String[] args) throws Exception {
		Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
		Logger.getLogger("org.xnio").setLevel(Level.SEVERE);

		testRemoteEJB();

	}

	private static void testRemoteEJB() throws NamingException {	
		
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		
		final TheatreInfo info = lookupTheatreInfoEJB();
		
		final TheatreBooker book = lookupTheatreBookerEJB();
		
		dumpWelcomeMessage();
		
		String command = ""; 
		Future<String> futureResult = null;
		 
		while (true){

			command = IOUtils.readLine("> ");
			if (command.equals("rezerwuj")) {

				int seatId = 0;

				try {
					seatId = IOUtils.readInt("Wpisz id miejsca");
				} catch (NumberFormatException e1) {
					logger.info("Niewłaściwy format!");
					continue;
				}

				try {
					String retVal = book.bookSeat(seatId-1);
					System.out.println(retVal);

				} 

				catch (SeatBookedException e) {
					logger.info(e.getMessage());
					continue;
				} 
				catch (NotEnoughMoneyException e) {
					logger.info(e.getMessage());
					continue;
				}
			}
			else if (command.equals("rezerwujasync")) {

				String text = IOUtils.readLine("Wpisz id miejsca");
				int seatId = 0;
				 
					try {
						seatId = Integer.parseInt(text);
					} catch (NumberFormatException e1) {
						logger.info("Niewłaściwy format!");
						continue;
					}
				 
                 
				 
					futureResult = book.bookSeatAsync(seatId-1);
					logger.info("Wysłano rezerwację. Sprawdź skrzynkę e-mail!"); 
			} 
			else if (command.equals("e-mail")) {
				if (futureResult == null || (!futureResult.isDone())) {
					logger.info("Nie otrzymano listu!");
					continue;
				}
				else {
				try {
					String result = futureResult.get();
					logger.info("Ostatnio otrzymany list: "+result);
					 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				continue;
				}
			}
			else if (command.equals("lista")) {
				logger.info(info.printSeatList().toString());
				continue;
			}
			else if (command.equals("koniec")) {
				logger.info("Żeganam");
				break;
			}
			else {
				logger.info("Nieznane polecenie "+command);

			}
		}





	}

 

	private static TheatreInfo lookupTheatreInfoEJB() throws NamingException {
		
		final Context context = new InitialContext(jndiProperties);
		return (TheatreInfo) context.lookup("ejb:/ticket-agency-cluster//TheatreInfoBean!com.packtpub.as7development.chapter10.ejb.TheatreInfo");		   

	}
	private static TheatreBooker lookupTheatreBookerEJB() throws NamingException {

		final Context context = new InitialContext(jndiProperties);
		return (TheatreBooker) context.lookup("ejb:/ticket-agency-cluster//TheatreBookerBean!com.packtpub.as7development.chapter10.ejb.TheatreBooker?stateful");		   

	}
	
	public static void dumpWelcomeMessage() {
		System.out.println("System rezerwacji biletów");
		System.out.println("=====================================");
		System.out.println("Polecenia: rezerwuj, rezerwujasync, lista, e-mail, koniec");
		
	}
}
