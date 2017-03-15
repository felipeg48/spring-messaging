package com.apress.messaging;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.messaging.domain.Exchange;
import com.apress.messaging.service.ExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class ReactorDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(ReactorDemoApplication.class);
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ReactorDemoApplication.class, args);
		System.in.read();
	}
	
	
	@Bean  
	CommandLineRunner rxJava(ExchangeService service){
		return args -> {
			
			log.info("Reactor >> Flux");
			Flux<Exchange> fluxExchage = service.getExchangeRates();
			//fluxExchage
			//	.subscribe( ex -> log.info(ex.toString()) );
			
			// Reactor's Schedulers
			fluxExchage
				.subscribeOn(Schedulers.parallel())
				.takeLast(10)
				.subscribe(ex -> log.info(ex.toString()));
			fluxExchage
				.subscribeOn(Schedulers.parallel())
				.subscribe(ex -> log.info(ex.toString()));
			
			// Custom Executors/Schedulers
			ExecutorService executor = Executors.newFixedThreadPool(100);
			fluxExchage
				.subscribeOn(Schedulers.fromExecutor(executor))
				.subscribe(ex -> log.info(ex.toString()));
			
			executor.awaitTermination(1, TimeUnit.MINUTES);
			executor.shutdown();
		};
	}
}
