package com.guilhermepalma.subscriberevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka // Define Kafka use in SpringBoot Application
public class SubscriberEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberEventsApplication.class, args);
    }

}
