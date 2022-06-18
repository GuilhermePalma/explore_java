package com.guilhermepalma.streamexercices.data.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "product_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name = "orderDate")
    private LocalDate orderDate;

    @Column(nullable = false, name = "deliveryDate")
    private LocalDate deliveryDate;

    @ManyToMany
    @JoinTable(
            name = "order_product_relationship",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Set<Product> productList;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
