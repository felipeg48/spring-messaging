package com.apress.messaging.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

//@Configuration
//@EnableWebSocketMessageBroker
//@EnableConfigurationProperties(SimpleWebSocketsProperties.class)
public class WebSocketsConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	SimpleWebSocketsProperties props;
	
	public WebSocketsConfig(SimpleWebSocketsProperties props){
		this.props = props;
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(props.getEndpoint()).withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(props.getTopic());
		config.setApplicationDestinationPrefixes(props.getAppDestinationPrefix());
	}
}
