package net.learntechnology.util;

import java.io.*;

import javax.jms.*;
//TODO: reference jboss at work
/**
 * <code>JmsProducer</code> encapsulates sending a JMS Message.
 *
 */
public class JmsProducer {

    private JmsProducer() {}

    public static void sendMessage(Serializable payload, String connectionFactoryJndiName, String destinationJndiName) throws JmsProducerException {
        try {
            ConnectionFactory connectionFactory = null;
            Connection connection = null;
            Session session = null;
            Destination destination = null;
            MessageProducer messageProducer = null;
            ObjectMessage message = null;
            System.out.println("In sendMessage of JmsProducter, getting ConnectionFactory for jndi name: "+connectionFactoryJndiName );
            connectionFactory = ServiceLocator.getJmsConnectionFactory(
                                                     connectionFactoryJndiName);

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = ServiceLocator.getJmsDestination(destinationJndiName);
            messageProducer = session.createProducer(destination);
            message = session.createObjectMessage(payload);
            messageProducer.send(message);
            System.out.println("Message sent to messageProducer");
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException je) {
            throw new JmsProducerException(je);
        } catch (ServiceLocatorException sle) {
            throw new JmsProducerException(sle);
        }
    }
}