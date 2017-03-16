package com.apress.messaging.controller;

import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apress.messaging.domain.Person;
import com.apress.messaging.repository.PersonRepository;

import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {

	SubscribableChannel personChannel;
	PersonRepository repo;
	
	public ReactiveController(SubscribableChannel personChannel, PersonRepository repo){
		this.personChannel = personChannel;
		this.repo = repo;
	}
	
	@GetMapping(value="/person-watcher", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Person> log4Person(){
		return Flux.create( sink -> {
			personChannel.subscribe(message -> sink.next((Person) message.getPayload()));
		});
				
	}
	
	@PostMapping(value="/person", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createPerson(@RequestBody Person person){
		repo.save(person);
		personChannel.send(MessageBuilder.withPayload(person).build());
	}
	
	
	
	
}
