package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.data.repository.ProductRepository;
import com.guilhermepalma.streamexercices.util.Util;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public GridView<Product> getProducts() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }


}
