package com.guilhermepalma.publisherevents.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderData {
    private String id;
    private String name;
    private BigDecimal value;
}
