package com.guilhermepalma.publisherevents.controller;

import com.guilhermepalma.publisherevents.data.OrderData;
import com.guilhermepalma.publisherevents.service.RegisterEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // Generate constructor with "final" params
public class OrderProvider {

    final private RegisterEventService registerEventService;

    @PostMapping("order/v1")
    public ResponseEntity<String> saveOrder(@RequestBody OrderData order) {
        registerEventService.addEvent("ADD_ORDER", order);
        return ResponseEntity.ok("Ok");
    }

}
