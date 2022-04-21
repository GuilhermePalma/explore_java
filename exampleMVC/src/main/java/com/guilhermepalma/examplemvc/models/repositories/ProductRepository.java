package com.guilhermepalma.examplemvc.models.repositories;

import com.guilhermepalma.examplemvc.models.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Interface que Herda os Metodos da Interface {@link PagingAndSortingRepository} que disponibiliza os Metodos necessarios
 * para a realização de um CRUD no Banco de Dados e tambem a Paginação e Ordenação de Resultados.
 * <p>
 * Na Herança da Inteface {@link PagingAndSortingRepository} é necessario Informar qual Classe será feito o Mapeamento e
 * qual o Tipo de Dado que foi aplicado no Atributo Identificador
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    /**
     * Metodo Implementado por meio da Convenção do Spring Bot que Obtem um {@link Iterable} de {@link Product} a partir
     * de um Segmento do seu nome. Esse metodo não faz distinção entre letras maiusculas e minusculas
     *
     * @param segmentName Segmento do Nome de um {@link Product} que se deseja consultar no Banco de Dados
     * @return {@link Iterable<Product>}
     */
    Iterable<Product> findByNameContainingIgnoreCase(String segmentName);

    /**
     * Metodo Implementado por meio da Convenção do Spring Bot que Obtem um {@link Iterable} de {@link Product} a partir
     * de um determinado preço para baixo.
     *
     * @param price Preço Base que os {@link Product} serão obtidos
     * @return {@link Iterable<Product>}
     */
    Iterable<Product> findByPriceLessThanEqual(Double price);

    /**
     * Metodo Implementado por meio da Convenção do Spring Bot que Obtem um {@link Iterable} de {@link Product} a partir
     * de um determinado preço para cima.
     *
     * @param price Preço Limite que os {@link Product} serão obtidos
     * @return {@link Iterable<Product>}
     */
    Iterable<Product> findByPriceGreaterThanEqual(Double price);

}
