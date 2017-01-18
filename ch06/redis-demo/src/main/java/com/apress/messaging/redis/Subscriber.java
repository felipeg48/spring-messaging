package com.apress.messaging.redis;

import org.springframework.stereotype.Component;

@Component
public class Subscriber {

	// If only one method define, it must be named: handleMessage
	public void handleMessage(String message){
		// Process message here ...
	}
}
