package com.guilhermepalma.streamexercices.api;

import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.service.ProductService;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductRestEndpoint {

    @Autowired
    private ProductService productService;

    @GetMapping("v1/products")
    public GridView<Product> getProducts() throws ChangeSetPersister.NotFoundException {
        Optional<GridView<Product>> results = Optional.ofNullable(productService.getProducts());
        return results.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

}
