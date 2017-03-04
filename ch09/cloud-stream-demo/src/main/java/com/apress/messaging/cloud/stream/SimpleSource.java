package com.apress.messaging.cloud.stream;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;


//@EnableBinding(Source.class)
public class SimpleSource {
	
	private SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss");
	
	@Bean
	@InboundChannelAdapter(channel=Source.OUTPUT)
	public MessageSource<String> simpleText(){
		
		return () -> MessageBuilder.withPayload("Hello at " +  simpleDate.format(new Date())).build();
		
		/*
		return new MessageSource<String>(){

			private List<String> justWords = Arrays.asList("hello","world","bye","see you later","nice to meet you");
			private SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss");
			private Random rand = new Random();
			
			@Override
			public Message<String> receive() {
				String word = justWords.get(rand.nextInt(justWords.size()));
				return MessageBuilder.withPayload(String.format("%s at %s", word,simpleDate.format(new Date()))).build();
			}
			
		};
		*/
	}
	
}
