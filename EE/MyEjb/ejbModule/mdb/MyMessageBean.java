package mdb;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


public class MyMessageBean implements MessageListener{

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
