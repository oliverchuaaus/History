package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/MyTopic") })
public class MyTopicBean implements MessageListener{

    @Override
    public void onMessage(Message msg) {
	try {
	    String trace = msg.getStringProperty("TRACE");
	    System.out.println(trace);
	} catch (JMSException e) {
	    e.printStackTrace();
	}
    }

}
