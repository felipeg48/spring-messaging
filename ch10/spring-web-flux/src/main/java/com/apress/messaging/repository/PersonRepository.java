package com.apress.messaging.repository;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.apress.messaging.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String>{
	
	//CompletableFuture<Person> findById(String id);
	
	@Query("{}")
	Stream<Person> all();
}