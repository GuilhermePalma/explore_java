package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.data.repository.ProductRepository;
import com.guilhermepalma.streamexercices.exceptions.NotFoundException;
import com.guilhermepalma.streamexercices.util.Util;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public GridView<Product> getProducts() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 1: Obtenha uma Lista de {@link Product} com a Categoria "Books" e Preço > 100
     */
    public GridView<Product> getProductsInBookCategoryAndPriceLess100() {
        List<Product> results = productRepository.findAll();

        List<Product> matches = results.stream()
                .filter(value -> value.getCategory().equalsIgnoreCase("Books") && value.getPrice() > 100.0)
                .collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 3: Obtenha uma lista de {@link Product} com a Categoria "Toys" e aplique 10% de Desconto
     */
    public GridView<Product> getProductsInToysCategoryAndApplyDiscount() {
        List<Product> results = productRepository.findAll();

        List<Product> matches = results.stream()
                .filter(value -> value.getCategory().equalsIgnoreCase("Toys"))
                .peek(value -> value.setPrice(value.getPrice() * 0.10))
                .collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 5: Obtenha o {@link Product} mais barato da categoria "Book"
     */
    public GridView<Product> getProductMoreCheapInBookCategory() throws NotFoundException {
        List<Product> results = productRepository.findAll();

        Product match = results.stream()
                .filter(value -> value.getCategory().equalsIgnoreCase("Book"))
                .max(Comparator.comparingDouble(Product::getPrice)).orElseThrow(NotFoundException::new);

        return new GridView<>(match, Long.valueOf(results.size()));
    }

    /**
     * Exercice 10: Obtenha os valores estaticos (ex: soma, media, maximo, minimo, total de elementos) para todos os {@link Product} da
     * categoria "Books"
     */
    public GridView<String> getStaticValuesOfProducts() {
        List<Product> results = productRepository.findAll();

        DoubleSummaryStatistics statistics = results.stream()
                .filter(value -> value.getCategory().equalsIgnoreCase("Books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

        String stringFormatted = String.format("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f",
                statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum());

        return Util.isNullOrEmpty(stringFormatted) ? null : new GridView<>(stringFormatted, Long.valueOf(results.size()));
    }

    /**
     * Exercice 14: Obtenha um mapeamento de dados (Map) com uma Lista de nomes de
     * {@link Product} por Categoria
     */
    public GridView<Map<String, List<String>>> getMapWithProductNameByCategory() {
        List<Product> results = productRepository.findAll();

        Map<String, List<String>> matches = results.stream().collect(Collectors.groupingBy(
                Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())
        ));

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 15: Obtenha o {@link com.guilhermepalma.streamexercices.data.model.Product} mais caro de cada Categoria
     */
    public GridView<Map<String, String>> getProductMoreExpansiveByCategory() {
        List<Product> results = productRepository.findAll();

        Map<String, String> matches = results.stream().collect(Collectors.groupingBy(
                Product::getCategory,
                // Executa uma Obteração de Collector e depois manipula o Resultado Final
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                        optionalValue -> optionalValue.map(Product::getName).orElse(null)
                )
        ));

        return new GridView<>(matches, Long.valueOf(results.size()));
    }

}
