package com.guilhermepalma.examplemvc.controllers;

import com.guilhermepalma.examplemvc.models.entities.Product;
import com.guilhermepalma.examplemvc.models.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = "application/json")
    public Product managerProduct(@RequestBody @Valid Product productRequest) {
        productRepository.save(productRequest);
        return productRequest;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        Product product = getProductById(id);

        if (product == null) return null;
        productRepository.delete(product);

        return product;
    }

}
