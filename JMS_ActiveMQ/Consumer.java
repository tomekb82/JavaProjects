import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Message;

public class Consumer {

	public static void main(String[] args) {

		try {

			// Create a ConnectionFactory

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(

					"admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);

			// Create a Connection

			Connection connection = connectionFactory.createConnection();

			connection.start();

			// Create a Session

			Session session = connection.createSession(false,

					Session.AUTO_ACKNOWLEDGE);

			// Create the destination

			Destination destination = session.createQueue("testQ");

			// Create a MessageConsumer from the Session to the Queue

			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message

			Message message = consumer.receive(1000);

			if (message instanceof TextMessage) {

				TextMessage textMessage = (TextMessage) message;

				String text = textMessage.getText();

				System.out.println("Text Message is " + text);

			}

			else {

				System.out.println(message);

			}

			consumer.close();

			session.close();

			connection.close();

		} catch (Exception e) {

			System.out.println(e);

			e.printStackTrace();

		}

	}

}
