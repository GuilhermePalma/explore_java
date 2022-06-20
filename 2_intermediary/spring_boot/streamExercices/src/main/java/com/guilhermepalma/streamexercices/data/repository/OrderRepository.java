package com.guilhermepalma.streamexercices.data.repository;

import com.guilhermepalma.streamexercices.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
