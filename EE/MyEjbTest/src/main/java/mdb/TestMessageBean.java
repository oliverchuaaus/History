package mdb;

import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

import junit.framework.TestCase;

public class TestMessageBean extends TestCase {
    public void testSendQueue() {
	QueueConnection cnn = null;
	QueueSender sender = null;
	QueueSession session = null;
	InitialContext ctx;
	try {
	    Properties props = new Properties();
	    props.setProperty("java.naming.factory.initial",
		    "org.jnp.interfaces.NamingContextFactory");
	    props.setProperty("java.naming.factory.url.pkgs",
		    "org.jboss.naming");
	    props.setProperty("java.naming.provider.url", "127.0.0.1:9099");

	    ctx = new InitialContext(props);
	    Queue queue = (Queue) ctx.lookup("queue/MyQueue");
	    QueueConnectionFactory factory = (QueueConnectionFactory) ctx
		    .lookup("ConnectionFactory");
	    cnn = factory.createQueueConnection();
	    session = cnn.createQueueSession(false,
		    QueueSession.AUTO_ACKNOWLEDGE);
	    TextMessage msg = session.createTextMessage();
	    msg.setStringProperty("TRACE", "Hello World1");
	    msg.setBooleanProperty("message1", true);
	    sender = session.createSender(queue);
	    sender.send(msg);
	    sender.close();
	    session.close();
	    System.out.println("Message sent successfully to remote queue.");
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
    }

    public void testSendTopic() {
	TopicConnection cnn = null;
	TopicPublisher publisher = null;
	TopicSession session = null;
	InitialContext ctx;
	try {
	    Properties props = new Properties();
	    props.setProperty("java.naming.factory.initial",
		    "org.jnp.interfaces.NamingContextFactory");
	    props.setProperty("java.naming.factory.url.pkgs",
		    "org.jboss.naming");
	    props.setProperty("java.naming.provider.url", "127.0.0.1:9099");

	    ctx = new InitialContext(props);
	    Topic topic = (Topic) ctx.lookup("topic/MyTopic");
	    TopicConnectionFactory factory = (TopicConnectionFactory) ctx
		    .lookup("ConnectionFactory");
	    cnn = factory.createTopicConnection();
	    session = cnn.createTopicSession(false,
		    TopicSession.AUTO_ACKNOWLEDGE);
	    TextMessage msg = session.createTextMessage();
	    msg.setStringProperty("TRACE", "Hello World1");
	    msg.setBooleanProperty("message1", true);
	    publisher = session.createPublisher(topic);
	    publisher.send(msg);
	    publisher.close();
	    session.close();
	    System.out.println("Message sent successfully to remote queue.");
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
    }
}