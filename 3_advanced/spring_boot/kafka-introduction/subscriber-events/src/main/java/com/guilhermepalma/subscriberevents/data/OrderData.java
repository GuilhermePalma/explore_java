package com.guilhermepalma.subscriberevents.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    private String id;
    private String name;
    private BigDecimal value;
}
