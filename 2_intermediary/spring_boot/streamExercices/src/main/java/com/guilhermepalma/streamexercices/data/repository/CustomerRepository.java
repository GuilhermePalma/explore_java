package com.guilhermepalma.streamexercices.data.repository;

import com.guilhermepalma.streamexercices.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
