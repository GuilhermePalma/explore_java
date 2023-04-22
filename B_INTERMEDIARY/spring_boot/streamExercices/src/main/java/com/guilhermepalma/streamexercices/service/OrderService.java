package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Customer;
import com.guilhermepalma.streamexercices.data.model.Order;
import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.data.repository.OrderRepository;
import com.guilhermepalma.streamexercices.util.Util;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public GridView<Order> getCustomers() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }


    /**
     * Exercice 4: Obtenha uma lista de {@link  com.guilhermepalma.streamexercices.data.model.Product}
     * encomendados pelo Tier=2 entre 01-Feb-2021 e 01-Apr-2021
     */
    public GridView<Product> getProductInOrderOfCustomer2() {
        List<Order> results = orderRepository.findAll();

        // Soma (1) no dia para Pegar o Intervalo Completo, do dia 01/02/2021 à 01/04/2021
        LocalDate initialDate = LocalDate.of(2021, 2, 1 + 1);
        LocalDate finalDate = LocalDate.of(2021, 4, 1 + 1);
        List<Product> matches = results.stream()
                .filter((value) -> value.getCustomer().getTier() == 2)
                .filter((value) -> value.getOrderDate().isAfter(initialDate) && value.getOrderDate().isBefore(finalDate))
                .flatMap(value -> value.getProductList().stream())
                .distinct()
                .collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 2: Obtenha uma Lista de {@link Order} em que os Produtos tenham a Categoria "Baby"
     */
    public GridView<Order> getOrderWithBabyCategory() {
        List<Order> results = orderRepository.findAll();

        List<Order> matches = results.stream().filter((value) -> {
            if (Util.isNullOrEmpty(value.getProductList())) return false;
            Set<String> categoriesOfValue = value.getProductList().stream()
                    .map(Product::getCategory)
                    .collect(Collectors.toSet());
            return categoriesOfValue.contains("Baby");
        }).collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 6: Obtenha as 3 {@link Order} mais Recentes
     */
    public GridView<Order> getTreeRecentOrder() {
        List<Order> results = orderRepository.findAll();

        // Obtem as Dadas na Ordem Decrescente (Menor data para a maior = Mais Recente)
        List<Order> matches = results.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3L)
                .collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 7: Obtenha uma lista de {@link Order}  do dia "15-Mar-2021", Imprima os Valores no Console e devolva a
     * Lista de {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Product> getOrderOfSpecifyDayAndPrintAndReturnProducts() {
        List<Order> results = orderRepository.findAll();

        LocalDate specifyDate = LocalDate.of(2021, 3, 15);
        List<Product> matches = results.stream()
                .filter((value) -> value.getOrderDate().equals(specifyDate))
                .flatMap(value -> value.getProductList().stream())
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(matches, Long.valueOf(results.size()));
    }

    /**
     * Exercice 8: Calcule o Valor Total de Todos os Pedidos em "Feb 2021"
     */
    public Double calValuesOfOrderInFeb2021() {
        List<Order> results = orderRepository.findAll();

        return results.stream()
                .filter((value) -> value.getOrderDate().getMonthValue() == 2 && value.getOrderDate().getYear() == 2021)
                .mapToDouble((value -> value.getProductList().stream().mapToDouble(Product::getPrice).sum()))
                .sum();
    }

    /**
     * Exercice 9: Calcule o valor medio pago nas {@link Order}  de "14-Mar-2021"
     */
    public OptionalDouble getAverageValueInOrderInSpecifyDay() {
        List<Order> results = orderRepository.findAll();

        // Obtem a Lista de Valores Unicos e Faz uma media dos Valores
        LocalDate specifyDate = LocalDate.of(2021, 3, 15);
        return results.stream()
                .filter(value -> value.getOrderDate().equals(specifyDate))
                .flatMap(value -> value.getProductList().stream())
                .mapToDouble(Product::getPrice)
                .average();
    }

    /**
     * Exercice 11: Obtenha um mapeamento de dados (Map) com o ID da {@link Order} e a quantidade ("count") de
     * {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Map<Long, Integer>> getMapWithOrderAndQuantityOfProducts() {
        List<Order> results = orderRepository.findAll();

        Map<Long, Integer> matches = results.stream()
                .collect(Collectors.toMap(Order::getId, e -> e.getProductList().size()));

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(Collections.singletonList(matches), Long.valueOf(results.size()));
    }

    /**
     * Exercice 12: Produza um mapeamento de dados (Map) com os registros das {@link Order} agrupados pelos
     * {@link Customer}
     */
    public GridView<Map<Customer, List<Order>>> getMapWithGroupByCustomer() {
        List<Order> results = orderRepository.findAll();

        Map<Customer, List<Order>> matches = results.stream().collect(Collectors.groupingBy(Order::getCustomer));

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(Collections.singletonList(matches), Long.valueOf(results.size()));
    }

    /**
     * Exercice 13: Produza um mapeamento de dados (Map) com a {@link Order} e o valor total dos
     * {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Map<Long, Double>> getMapWithRegistersAndTotalValue() {
        List<Order> results = orderRepository.findAll();

        Map<Long, Double> matches = results.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        e -> e.getProductList().stream().mapToDouble(Product::getPrice).sum())
                );

        return Util.isNullOrEmpty(matches) ? null : new GridView<>(Collections.singletonList(matches), Long.valueOf(results.size()));
    }

}
