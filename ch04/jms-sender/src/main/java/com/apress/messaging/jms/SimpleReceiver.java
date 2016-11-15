package com.apress.messaging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiver {

	@JmsListener(destination = "${apress.jms.queue}")
	public void processMessage(String content) {
	
	}

}
