package com.apress.messaging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.apress.messaging.exception.BadCodeRuntimeException;

@Aspect
@Component
public class CurrencyConversionAudit {
	private static final String DASH_LINE = "===================================";
	private static final String NEXT_LINE = "\n";
	private static final Logger log = LoggerFactory.getLogger(CurrencyConversionAudit.class);
	@Pointcut("execution(* com.apress.messaging.service.*Service.*(..))")
    public void exceptionPointcut() {}
	
	@AfterThrowing(pointcut="exceptionPointcut()", throwing="ex")
	public void badCodeException(JoinPoint jp, BadCodeRuntimeException ex){
		StringBuilder str = new StringBuilder(NEXT_LINE);
		str.append(DASH_LINE);
		str.append(NEXT_LINE);
		str.append("  Class: " + jp.getTarget().getClass().getSimpleName());
		str.append(NEXT_LINE);
		str.append(" Method: " + jp.getSignature().getName());
		str.append(NEXT_LINE);
		str.append(NEXT_LINE);
		str.append("  Error: " + ex.getMessage());
		str.append(NEXT_LINE);
		
		if(ex.getConversion()!=null)
			str.append(" Object: " + ex.getConversion());
		if(ex.getRate()!=null)
			str.append(" Object: " + ex.getRate());
		
		str.append(NEXT_LINE);
		str.append(DASH_LINE);
		log.error(str.toString());
		
	}
	
}
