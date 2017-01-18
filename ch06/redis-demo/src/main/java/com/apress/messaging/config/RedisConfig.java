package com.apress.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.apress.messaging.redis.Subscriber;

@Configuration
@EnableConfigurationProperties(SimpleRedisProperties.class)
public class RedisConfig {

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter, @Value("${apress.redis.topic}") String topic) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);		
		container.addMessageListener(listenerAdapter, new PatternTopic(topic));
		
		/* Un-comment that if you need an extra subscriber 
		container.addMessageListener(
				(message, pattern) ->{
				
					System.out.println("Pattern: " + new String(pattern));
					System.out.println("Message: " + message);
				
			}
		, new PatternTopic(topic));
		*/
		
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Subscriber subscriber) {
		return new MessageListenerAdapter(subscriber);
	}
}
