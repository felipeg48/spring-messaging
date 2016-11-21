package com.apress.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AMQPProperties.class)
public class AMQPConfig {

	/* Uncomment this out for a MessageListener implementation
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListener consumer, @Value("${apress.amqp.queue}")String queueName) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(consumer);
		return container;
	}
	*/
	
	//Converters
	
	/*
	//Producer
	@Bean
	public RabbitTemplate rabbitTemple(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	
	//Consumer
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setMessageConverter(new Jackson2JsonMessageConverter());
	    return factory; 
	}
	*/
	
	//Queues
	
	
	@Bean
	public Queue queue(@Value("${apress.amqp.queue}")String queueName){
		return new Queue(queueName,false);
	}
	
	
	/*
	@Bean
	public Queue rateQueue(@Value("${apress.amqp.rate-queue}")String queueName){
		return new Queue(queueName,false);
	}
	*/
}
