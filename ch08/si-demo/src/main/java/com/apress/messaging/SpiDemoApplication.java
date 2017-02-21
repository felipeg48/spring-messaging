package com.apress.messaging;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpiDemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpiDemoApplication.class)
		.web(false) // True for using a Web app and the /h2-console endpoint.
		.run(args);
	}
	
	/* Enable this for a Simple Example */
	
	/*
	@Bean
	CommandLineRunner process(MessageChannel input){
		return args -> {
			Message<String> message = MessageBuilder.withPayload("World").build();
			input.send(message);
		};
	}
	*/
	
	
	/* Enable this for the Rate to RabbitMQ, make sure you have RabbitMQ up and running */
	
	/*
	@Bean
	CommandLineRunner processRate(MessageChannel amqpChannel){
		return args -> {
			amqpChannel.send(MessageBuilder.withPayload(new Rate("EUR",0.88857F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("JPY",102.17F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("MXN",19.232F,new Date())).build());
			amqpChannel.send(MessageBuilder.withPayload(new Rate("GBP",0.75705F,new Date())).build());
		};
	}
	*/
}
