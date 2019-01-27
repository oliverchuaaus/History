package hello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

	@JmsListener(destination = "mailbox.topic", containerFactory = "topicListenerFactory")
	public void receiveMessage(Email email) {
		System.out.println("Topic Received <" + email + ">");
	}

}