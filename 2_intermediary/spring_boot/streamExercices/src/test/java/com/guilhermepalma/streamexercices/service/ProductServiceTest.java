package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.data.repository.ProductRepository;
import com.guilhermepalma.streamexercices.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    void getProductsInBookCategoryAndPriceLess100() {
        List<Product> result = productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        assertEquals(result, productService.getProductsInBookCategoryAndPriceLess100().getUniqueOrMultipleValues());
    }

    @Test
    void getProductsInToysCategoryAndApplyDiscount() {
        List<Product> result = productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
                .map(p -> {
                    p.setPrice(p.getPrice() * 0.1);
                    return p;
                })
                .collect(Collectors.toList());

        assertEquals(result, productService.getProductsInToysCategoryAndApplyDiscount().getUniqueOrMultipleValues());
    }

    @Test
    void getProductMoreCheapInBookCategory() {
        Optional<Product> result = productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .min(Comparator.comparing(Product::getPrice));

        try {
            assertEquals(result, productService.getProductMoreCheapInBookCategory().getUniqueOrMultipleValues());
        } catch (NotFoundException e) {
            System.out.println("Not expected exception");
        }
    }

    @Test
    void getStaticValuesOfProducts() {
        DoubleSummaryStatistics statistics = productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

        String results = String.format("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f",
                statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum());

        assertEquals(results, productService.getStaticValuesOfProducts().getUniqueOrMultipleValues());
    }

    @Test
    void getMapWithProductNameByCategory() {
        Map<String, List<String>> result = productRepository.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.mapping(Product::getName, Collectors.toList()))
                );
        assertEquals(result, productService.getMapWithProductNameByCategory().getUniqueOrMultipleValues());
    }

    @Test
    void getProductMoreExpansiveByCategory() {
        Map<String, String> result = productRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                                optionalProduct -> optionalProduct.map(Product::getName).orElse(null)
                        )
                ));

        assertEquals(result, productService.getProductMoreExpansiveByCategory().getUniqueOrMultipleValues());
    }
}