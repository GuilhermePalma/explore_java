package com.guilhermepalma.examplemvc.controllers;

import com.guilhermepalma.examplemvc.models.entities.Product;
import com.guilhermepalma.examplemvc.models.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // O Spring atribui uma Implementação dentro dessa Interface (Injeção de Dependencia)
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Product newProduct(@RequestBody Product productRequest) {
        productRepository.save(productRequest);

        return productRequest;
    }

    @DeleteMapping
    public Product deleteProduct(@RequestParam int id) {
        Product product = getProductById(id);

        if (product == null) return null;
        productRepository.delete(product);

        return product;
    }

}
