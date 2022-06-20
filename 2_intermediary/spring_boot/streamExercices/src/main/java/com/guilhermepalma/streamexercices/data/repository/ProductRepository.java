package com.guilhermepalma.streamexercices.data.repository;

import com.guilhermepalma.streamexercices.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
