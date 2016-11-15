package com.apress.messaging.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableConfigurationProperties(JMSProperties.class)
public class JMSConfig {

	/*
	@Bean
    public DefaultJmsListenerContainerFactory customFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
	
	@Configuration
	public static class ListenerConfig implements JmsListenerConfigurer{

		private MessageListener queueListener;
		private String destinationName;
		
		public ListenerConfig(MessageListener queueListener, @Value("${apress.jms.queue}") final String destinationName){
			this.queueListener = queueListener;
			this.destinationName = destinationName;
		}
		
		@Override
		public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
			SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
	        endpoint.setId(UUID.randomUUID().toString());
	        endpoint.setDestination(this.destinationName);
	        endpoint.setMessageListener(this.queueListener);
	        registrar.registerEndpoint(endpoint);
		}
	}
	*/
	
	/*
	@Bean
	public DefaultMessageListenerContainer customMessageListenerContainer(ConnectionFactory connectionFactory, MessageListener queueListener, @Value("${apress.jms.queue}") final String destinationName){
		DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
		listener.setConnectionFactory(connectionFactory);
		listener.setDestinationName(destinationName);
		listener.setMessageListener(queueListener);
		return listener;
	}
	*/
	
	
	@Bean 
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
}
