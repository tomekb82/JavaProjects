package com.packtpub.as7development.chapter7.service;



import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "MDBService", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/queue/ticketQueue"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'HIGH'"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class MDBService implements MessageListener {

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
