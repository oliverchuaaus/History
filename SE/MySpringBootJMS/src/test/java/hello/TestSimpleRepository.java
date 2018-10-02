package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimpleRepository {
	@Autowired
	JmsTemplate topicJmsTemplate;
	@Autowired
	JmsTemplate queueJmsTemplate;


	@Test
	public void testSendQueueMessage() {
		System.out.println("Sending an email message.");
		topicJmsTemplate.convertAndSend("mailbox.topic", new Email("kris@example.com", "Hello Kris Topic"));
		queueJmsTemplate.convertAndSend("mailbox.queue", new Email("kris@example.com", "Hello Kris Queue"));

		topicJmsTemplate.convertAndSend("mailbox.topic", new Email("kris@example.com", "Hello Kris Topic2"));
		queueJmsTemplate.convertAndSend("mailbox.queue", new Email("kris@example.com", "Hello Kris Queue2"));

	}

}
