package eu.tbelina.jboss.service;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(name = "MDBLowPriorityService", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/queue/ticketQueue"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'LOW'"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class MDBLowPriorityService implements MessageListener {

	@Inject
	private Logger logger;
 
	public void onMessage(Message message) {

		TextMessage tm = (TextMessage) message;
		try {
			logger.info("Otrzymano komunikat "+tm.getText());

		} catch (JMSException e) {

			e.printStackTrace();
		}
	}
}
