package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStreamDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamDemoApplication.class, args);
	}
	
	/*
	@Bean
	CommandLineRunner sourceSender(Source source){
		return args ->{
			
			source.output().send(MessageBuilder.withPayload("hello world").build());
		};
	}
	*/
}
