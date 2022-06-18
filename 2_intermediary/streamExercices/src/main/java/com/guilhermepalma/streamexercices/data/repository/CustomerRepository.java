package com.guilhermepalma.streamexercices.data.repository;

import com.guilhermepalma.streamexercices.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
