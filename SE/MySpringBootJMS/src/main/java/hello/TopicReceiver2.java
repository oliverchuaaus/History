package hello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver2 {

	@JmsListener(destination = "mailbox.topic", containerFactory = "topicListenerFactory")
	public void receiveMessage(Email email) {
		System.out.println("Topic2 Received <" + email + ">");
	}

}