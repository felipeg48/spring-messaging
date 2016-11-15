package com.apress.messaging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedReceiver {

	@JmsListener(destination = "${apress.jms.queue}")
	public void processMessage(String content) {
	
	}

}
