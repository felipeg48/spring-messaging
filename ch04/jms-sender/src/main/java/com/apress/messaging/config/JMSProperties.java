package com.apress.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="apress.jms")
public class JMSProperties {

	private String queue;
	private String rateQueue;
	private String rateReplyQueue;

	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getRateQueue() {
		return rateQueue;
	}
	public void setRateQueue(String rateQueue) {
		this.rateQueue = rateQueue;
	}
	public String getRateReplyQueue() {
		return rateReplyQueue;
	}
	public void setRateReplyQueue(String rateReplyQueue) {
		this.rateReplyQueue = rateReplyQueue;
	}
	
}
