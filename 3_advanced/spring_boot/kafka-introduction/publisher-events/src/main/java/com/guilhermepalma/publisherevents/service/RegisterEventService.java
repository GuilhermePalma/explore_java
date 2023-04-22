package com.guilhermepalma.publisherevents.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterEventService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public <T> void addEvent(String group, T value) {
        kafkaTemplate.send(group, value);
    }

}
