package com.guilhermepalma.examplemvc.controllers;

import com.guilhermepalma.examplemvc.models.entities.Product;
import com.guilhermepalma.examplemvc.models.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe marcada como {@link RestController}, responsavel por lidar com requisições da API
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    /**
     * Instancia da Classe {@link ProductRepository} responsavel pelas Operações com o Banco de Dados. Atribui uma
     * Implementação da propria interface por meio da Injeção de Dependencia feita pelo proprio Spring Bot ao
     * Inicializar a Classe
     */
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Metodo que utiliza da URI Principal para Retornar uma Lista de Produtos. Tambem permite com que realiza a Consulta
     * de um Produto pelo ID e implementa a Páginação por meio da Interface {@link Pageable}
     *
     * @return {@link Iterable<Product>}|null
     */
    @GetMapping
    public Iterable<Product> getProducts(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0", required = false, name = "page") int numberPages,
            @RequestParam(defaultValue = "15", required = false) int quantityResults) {

        if (id != null) return Collections.singletonList(productRepository.findById(id).orElse(null));

        if (quantityResults > 35) quantityResults = 30;

        Pageable pageable = PageRequest.of(numberPages, quantityResults);
        return productRepository.findAll(pageable);
    }

    /**
     * Metodo Responsavel por realizar uma Busca de Itens na API utilizando Filtros como o Nome e
     *
     * @return {@link Iterable<Product>}|null
     */
    @GetMapping("/search")
    public Iterable<Product> searchProduct(
            @RequestParam(name = "keywords", required = false, defaultValue = "") String segmentName,
            @RequestParam(name = "priceGreater", required = false, defaultValue = "0") Integer priceGreater,
            @RequestParam(name = "priceLess", required = false) Integer priceLess) {

        List<Product> keywordResults = (List<Product>) productRepository.findByNameContainingIgnoreCase(segmentName);
        List<Product> priceLessResults = (List<Product>) productRepository.findByPriceLessThanEqual(priceLess != null ? priceLess : Double.MAX_VALUE);
        List<Product> priceGreaterResults = (List<Product>) productRepository.findByPriceGreaterThanEqual(Double.valueOf(priceGreater));

        // Realiza Comparações para Obter o Valor Final
        return keywordResults.stream().filter(productKeyword -> priceGreaterResults.stream().map(Product::getId)
                // Compara se possuem ID iguais
                .anyMatch(productPriceGreater -> productPriceGreater.equals(productKeyword.getId()))).collect(Collectors.toList()).stream().filter(resultsProducts -> priceLessResults.stream().map(Product::getId).anyMatch(productPressLess -> productPressLess.equals(resultsProducts.getId()))).collect(Collectors.toList());
    }

    /**
     * Metodo Responsavel por Salvar e Alterar o Produto conforme o Metodo HTTP Informado. Recebe uma Instancia do
     * {@link Product} e valida com as Informações do Banco de Dados
     *
     * @return {@link Product}
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = "application/json")
    public Product managerProduct(@RequestBody @Valid Product productRequest) {
        productRepository.save(productRequest);
        return productRequest;
    }

    /**
     * Metodo Responsavel por excluir um Produto pelo ID, caso ele Exista.
     *
     * @return {@link Product}|null
     */
    @DeleteMapping
    public Product deleteProduct(@RequestParam Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) productRepository.delete(product);

        return product;
    }

}
