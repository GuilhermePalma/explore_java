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

    /**
     * Exercice 1: Obtenha uma Lista de {@link Product} com a Categoria "Books"  e Preço > 100
     */
    public GridView<Product> getProductsInBookCategoryAndPriceLess100() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 3: Obtenha uma lista de {@link Product} com a Categoria "Toys" e aplique 10% de Desconto
     */
    public GridView<Product> getProductsInToysCategoryAndApplyDiscount() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 5: Obtenha o {@link Product} mais barato da categoria "Book"
     */
    public GridView<Product> getProductMoreCheapInBookCategory() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 10: Obtenha os valores estaticos (ex: soma, media, maximo, minimo, total de elementos) para todos os {@link Product} da
     * categoria "Books"
     */
    public GridView<Product> getStaticValuesOfProducts() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 14: Obtenha um mapeamento de dados (Map) com uma Lista de nomes de
     * {@link Product} por Categoria
     */
    public GridView<Product> getMapWithProductNameByCategory() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 15: Obtenha o {@link com.guilhermepalma.streamexercices.data.model.Product} mais caro de cada Categoria
     */
    public GridView<Product> getProductMoreExpansiveByCategory() {
        List<Product> results = productRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

}
