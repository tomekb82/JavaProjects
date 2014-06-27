package com.packtpub.as7development.chapter7.service;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.*;


@RequestScoped
public class JMSService {


	@Resource(mappedName = "java:jboss/jms/queue/ticketQueue")
	private Queue queueExample;

	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory cf;

	private Connection connection;
	public void sendMessage(String txt, String priority) {


		try {         

			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = null;

			publisher = session.createProducer(queueExample);

			connection.start();

			TextMessage message = session.createTextMessage(txt);
			message.setStringProperty("priority", priority);
			publisher.send(message);


		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {         


			if (connection != null)   {
				try {
					connection.close();
				} catch (JMSException e) {                    
					e.printStackTrace();
				}

			}
		}
	} 
}
