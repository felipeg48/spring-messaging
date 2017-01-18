package com.apress.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootApplication
public class RedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner sendMessage(StringRedisTemplate template, @Value("${apress.redis.topic}")String topic){
		return args -> {
			template.convertAndSend(topic, "Hello Redis with Spring Boot!");
		};
	}
}
