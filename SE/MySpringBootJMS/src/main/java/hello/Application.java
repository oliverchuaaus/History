package hello;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Application {
	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		factory.setPubSubDomain(true);
		// This provides all boot's default to this factory, including the message
		// converter
		return factory;
	}

	@Bean
	public JmsTemplate topicJmsTemplate(ConnectionFactory topicListenerFactory,
			MessageConverter jacksonJmsMessageConverter) {
		JmsTemplate template = new JmsTemplate(topicListenerFactory);
		template.setMessageConverter(jacksonJmsMessageConverter);
		template.setPubSubDomain(true);
		return template;
	}

	@Bean
	public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// factory.setPubSubDomain(true);
		// This provides all boot's default to this factory, including the message
		// converter
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public JmsTemplate queueJmsTemplate(ConnectionFactory queueListenerFactory,
			MessageConverter jacksonJmsMessageConverter) {
		JmsTemplate template = new JmsTemplate(queueListenerFactory);
		template.setMessageConverter(jacksonJmsMessageConverter);
		return template;
	}

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		JmsTemplate jmsTemplate = context.getBean("queueJmsTemplate", JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending an email message.");
		jmsTemplate.convertAndSend("mailbox.queue", new Email("info@example.com", "Hello"));
	}

}