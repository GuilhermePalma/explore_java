package com.guilhermepalma.publisherevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka // Define Kafka use in SpringBoot Application
@SpringBootApplication
public class PublisherEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherEventsApplication.class, args);
	}

}
