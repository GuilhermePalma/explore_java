package com.guilhermepalma.examplemvc.models.repositories;

import com.guilhermepalma.examplemvc.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
