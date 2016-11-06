package com.apress.messaging.event;

import org.springframework.context.ApplicationEvent;

import com.apress.messaging.domain.CurrencyConversion;

public class CurrencyConversionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4481493963350551884L;
	private CurrencyConversion conversion;
	
	public CurrencyConversionEvent(Object source, CurrencyConversion conversion) {
		super(source);
		this.conversion = conversion;
	}

	public CurrencyConversion getConversion(){
		return conversion;
	}
}
