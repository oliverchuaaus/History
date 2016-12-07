package net.learntechnology.util;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * <code>ServiceLocator</code> encapsulates JNDI lookups to make it
 * easier to access JNDI-based resources (EJBs, DataSources,
 * JMS Destinations, and so on).
 *
 */
public class ServiceLocator {

    /**
     * Making the default (no arg) constructor private
     * ensures that this class cannnot be instantiated.
     */
    private ServiceLocator() {}
 
    /**
     * Gets a JMS <code>ConnectionFactory</code> using the given JNDI name.
     *
     * @param jmsConnectionFactoryJndiName The JMS <code>ConnectionFactory</code>'s JNDI name.
     *
     * @return ConnectionFactory The JMS <code>ConnectionFactory</code>.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.jms.ConnectionFactory
     */
    public static ConnectionFactory getJmsConnectionFactory(String jmsConnectionFactoryJndiName) throws ServiceLocatorException {
        ConnectionFactory jmsConnectionFactory = null;

        try {
            Context ctx = new InitialContext();
            jmsConnectionFactory = (ConnectionFactory) ctx.lookup(jmsConnectionFactoryJndiName);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return jmsConnectionFactory;
    }

    /**
     * Gets a JMS <code>Destination</code> using the given JNDI name.
     *
     * @param jmsDestinationJndiName The JMS <code>Destination</code>'s JNDI name.
     *
     * @return Destination The JMS <code>Destination</code>.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.jms.Destination
     */
    public static Destination getJmsDestination(String jmsDestinationJndiName) throws ServiceLocatorException {
        Destination jmsDestination = null;

        try {
            Context ctx = new InitialContext();
            jmsDestination = (Destination) ctx.lookup(jmsDestinationJndiName);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return jmsDestination;
    }

}
