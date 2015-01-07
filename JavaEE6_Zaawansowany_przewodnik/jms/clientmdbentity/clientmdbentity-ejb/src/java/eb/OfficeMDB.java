/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package eb;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * The OfficeMDB class is a message-driven bean. It
 * implements the javax.jms.MessageListener interface. It
 * is defined as public (but not final or abstract).
 */
@MessageDriven(mappedName = "jms/Topic", activationConfig =  {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    , @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    , @ActivationConfigProperty(propertyName = "clientId", propertyValue = "OfficeMDB")
    , @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "OfficeMDB")
}
)
public class OfficeMDB implements MessageListener {
    static final Logger logger = Logger.getLogger("OfficeMDB");
    @Resource
    public MessageDrivenContext mdc;
    @PersistenceContext
    EntityManager em;
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    private Random processingTime = new Random();

    /**
     * Constructor, which is public and takes no arguments.
     */
    public OfficeMDB() {
    }

    /**
     * onMessage method, declared as public (but not final or
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a MapMessage, retrieves its
     * contents, and assigns the new hire to an office. Calls the
     * compose method to store the information in the persistence
     * entity and, if work is complete, to send a reply message
     * to the client.
     *
     * @param inMessage    the incoming message
     */
    @Override
    public void onMessage(Message inMessage) {
        MapMessage msg;
        String key;
        String name;
        String position;
        int officeNumber;

        try {
            if (inMessage instanceof MapMessage) {
                msg = (MapMessage) inMessage;
                key = msg.getString("HireID");
                name = msg.getString("Name");
                position = msg.getString("Position");
                logger.log(
                        Level.INFO,
                        "OfficeMDB.onMessage:"
                        + " Otrzymano komunikat dla pracownika o identyfikatorze {0}",
                        key);

                officeNumber = new Random().nextInt(300) + 1;

                /* Simulate processing time taking 1 to 10
                 * seconds.
                 */
                Thread.sleep(processingTime.nextInt(10) * 1000);
                compose(key, name, officeNumber, msg);
            } else {
                logger.log(
                        Level.WARNING,
                        "OfficeMDB.onMessage: " + "Komunikat niewłaściwego typu: {0}",
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(
                    Level.SEVERE,
                    "OfficeMDB.onMessage: Wyjątek JMSException: {0}",
                    e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            logger.log(
                    Level.SEVERE,
                    "OfficeMDB.onMessage: Wyjątek: {0}",
                    te.toString());
        }
    }

    /**
     * compose method, helper to onMessage method.
     *
     * Locates the row of the database represented by the primary
     * key and adds the office number allocated for the new hire.
     *
     * @param key           employee ID, primary key
     * @param name          employee name
     * @param officeNumber  office number
     * @param msg           the message received
     */
    void compose(
        String key,
        String name,
        int officeNumber,
        Message msg) {
        int num = 0;
        SetupOffice so = null;
        Connection connection = null;
        Session session;
        MessageProducer producer;
        MapMessage replyMsg;
        Destination replyDest;
        String replyCorrelationMsgId;
        boolean done = false;

        try {
            so = em.find(SetupOffice.class, key);

            if (so != null) {
                logger.log(
                        Level.INFO,
                        "OfficeMDB.compose: "
                        + "Znaleziono wspólną encję dla pracownika o identyfikatorze {0}",
                        key);
            }
        } catch (IllegalArgumentException iae) {
            logger.warning("OfficeMDB.compose: No join entity found");
        } catch (Exception e) {
            logger.severe(
                    "OfficeMDB.compose: em.find zawiodło bez"
                    + " zgłoszenia IllegalArgumentException");
        }

        // No entity found; create it
        if (so == null) {
            try {
                logger.log(
                        Level.INFO,
                        "OfficeMDB.compose: "
                        + "Tworzenie wspólnej encji dla pracownika o identyfikatorze {0}",
                        key);
                so = new SetupOffice(key, name);
                em.persist(so);
            } catch (Exception e) {
                logger.warning(
                        "OfficeMDB.compose: " + "Nie można było utworzyć wspólnej encji");
                mdc.setRollbackOnly();
            }
        }

        // Entity found or created, so add office
        if (so != null) {
            try {
                done = so.doOfficeNumber(officeNumber);
            } catch (Exception e) {
                logger.log(
                        Level.SEVERE,
                        "OfficeMDB.compose: "
                        + "Nie uzyskano biura dla pracownika o identyfikatorze {0}",
                        key);
                mdc.setRollbackOnly();
            }
        }

        /* Whichever bean receives the information that the setup is
         * complete sends a message back to the client and removes
         * the entity.
         */
        if (done) {
            try {
                connection = connectionFactory.createConnection();
            } catch (Exception ex) {
                logger.log(
                        Level.SEVERE,
                        "OfficeMDB.compose: "
                        + "Nie uzyskano połączenia do dostawcy JMS: {0}",
                        ex.toString());
            }

            try {
                /*
                 * Send reply to messages aggregated by this
                 * composite entity.  Call createReplyMsg
                 * to construct the reply.
                 */
                replyDest = msg.getJMSReplyTo();
                replyCorrelationMsgId = msg.getJMSMessageID();
                session = connection.createSession(true, 0);
                producer = session.createProducer(replyDest);
                replyMsg = createReplyMsg(so, session, replyCorrelationMsgId);
                producer.send(replyMsg);
                logger.log(
                        Level.INFO,
                        "OfficeMDB.compose: "
                        + "Wysłano komunikat odpowiedzi dla pracownika o identyfikatorze {0}",
                        so.getEmployeeId());
            } catch (JMSException je) {
                logger.log(
                        Level.SEVERE,
                        "OfficeMDB.compose: " + "Wyjątek JMSException: {0}",
                        je.toString());
            } catch (Exception e) {
                logger.log(
                        Level.SEVERE,
                        "OfficeMDB.compose: " + "Wyjątek: {0}",
                        e.toString());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException je) {
                        logger.log(
                                Level.SEVERE,
                                "OfficeMDB.compose: " + "Wyjątek JMSException: {0}",
                                je.toString());
                    }
                }
            }

            logger.log(
                    Level.INFO,
                    "OfficeMDB.compose: "
                    + "REMOVING SetupOffice entity employeeId={0}, Name={1}",
                    new Object[] { so.getEmployeeId(), so.getEmployeeName() });
            em.remove(so);
        }
    }

    /**
     * The createReplyMsg method composes the reply message
     * with the new hire information.
     *
     * @param session   the Session object for the message
     *                  producer
     * @param msgId        the reply correlation message ID
     *
     * @return  a MapMessage containing the reply message
     */
    private MapMessage createReplyMsg(
        SetupOffice so,
        Session session,
        String msgId) throws JMSException {
        MapMessage replyMsg = session.createMapMessage();
        replyMsg.setString(
            "employeeId",
            so.getEmployeeId());
        replyMsg.setString(
            "employeeName",
            so.getEmployeeName());
        replyMsg.setString(
            "equipmentList",
            so.getEquipmentList());
        replyMsg.setInt(
            "officeNumber",
            so.getOfficeNumber());
        replyMsg.setJMSCorrelationID(msgId);

        return replyMsg;
    }
}
