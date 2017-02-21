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
}
