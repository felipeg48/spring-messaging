package com.apress.messaging.listener;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.apress.messaging.annotation.Log;

@Component
public class RestAppEventListener {

	@EventListener
	@Log(printParamsValues=true)
	public void restAppHandler(SpringApplicationEvent springApp){
	}
}
