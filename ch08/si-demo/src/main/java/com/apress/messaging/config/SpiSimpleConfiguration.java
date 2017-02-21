package com.apress.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;

/* Enable the following annotation to use the XML configuration. Comment out all the @Configuration files. */ 

//@ImportResource({"META-INF/spring/integration/spi-context.xml"})
//@Configuration
public class SpiSimpleConfiguration {

	
	/* You can add a declaration like the following or you can use @Component in the class */
	/*
	@Bean
	public SimpleMessageHandler simpleMessageHandler(){
		return new SimpleMessageHandler();
	}
	*/
	
	@Bean
	public MessageChannel input(){
		return MessageChannels.direct().get();
	}
	
	@Bean
	public IntegrationFlow simpleFlow(){
		return IntegrationFlows.from("input")
				.filter("World"::equals)
				.transform("Hello "::concat)
				.handle("simpleMessageHandler","process")
				.get();
	}
	
}
