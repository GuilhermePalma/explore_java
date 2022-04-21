package com.guilhermepalma.examplemvc.models.repositories;

import com.guilhermepalma.examplemvc.models.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Iterable<Product> findByNameContainingIgnoreCase(String segmentName);

    Iterable<Product> findByPriceLessThanEqual(Double price);

    Iterable<Product> findByPriceGreaterThanEqual(Double price);

}
