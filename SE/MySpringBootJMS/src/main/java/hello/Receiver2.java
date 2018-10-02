package hello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver2 {

    @JmsListener(destination = "mailbox.queue", containerFactory = "queueListenerFactory")
    public void receiveMessage(Email email) {
        System.out.println("Queue Received <" + email + ">");
    }

}