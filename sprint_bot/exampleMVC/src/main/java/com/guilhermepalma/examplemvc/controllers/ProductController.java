package com.guilhermepalma.examplemvc.controllers;

import com.guilhermepalma.examplemvc.models.entities.Product;
import com.guilhermepalma.examplemvc.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired // O Spring atribui uma Implementação dentro dessa Interface (Injeção de Dependencia)
    private ProductRepository productRepository;


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping
    public @ResponseBody
    Product newProduct(@RequestParam String name) {
        Product product = new Product(name);
        productRepository.save(product);

        return product;
    }

    @DeleteMapping
    public Product deleteProduct(@RequestParam int id) {
        Product product = getProductById(id);

        if (product == null) return null;
        productRepository.delete(product);

        return product;
    }

}
