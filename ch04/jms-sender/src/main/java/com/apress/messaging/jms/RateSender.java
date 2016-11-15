package com.apress.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.apress.messaging.domain.Rate;

@Component
public class RateSender {

	private JmsTemplate jmsTemplate;
	
	@Autowired
	public RateSender(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendCurrency(String destination, Rate rate){
		this.jmsTemplate.convertAndSend(destination,rate);	
	}
	
	@JmsListener(destination="${apress.jms.rate-reply-queue}")
	public void process(String body,@Header("ID") String id){
		
	}
}
