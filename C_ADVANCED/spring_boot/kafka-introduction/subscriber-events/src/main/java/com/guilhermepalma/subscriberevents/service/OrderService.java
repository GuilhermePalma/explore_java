package com.guilhermepalma.subscriberevents.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilhermepalma.subscriberevents.data.OrderData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @KafkaListener(topics = "ADD_ORDER", groupId = "publisherevents")
    private void upsertOrder(ConsumerRecord<String, String> data) {
        log.info("Header: {}", data.headers());
        log.info("Key: {}", data.key());
        log.info("Partition: {}", data.partition());

        String value = data.value();
        log.debug("Value in Byter: {}", value);

        OrderData order = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            order = mapper.readValue(value, OrderData.class);
        } catch (Exception ex) {
            log.error("error in deserialize event", ex);
        }

        log.info("event recived: {}", order);
    }

}
