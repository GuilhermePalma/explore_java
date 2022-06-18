package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Customer;
import com.guilhermepalma.streamexercices.data.model.Order;
import com.guilhermepalma.streamexercices.data.repository.OrderRepository;
import com.guilhermepalma.streamexercices.util.Util;
import com.guilhermepalma.streamexercices.view.GridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public GridView<Order> getProductInOrderOfCustomer2() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 2: Obtenha uma Lista de {@link Order} em que os Produtos tenham a Categoria "Baby"
     */
    public GridView<Order> getOrderWithBabyCategory() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 6: Obtenha as 3 {@link Order} mais Recentes
     */
    public GridView<Order> getTreeRecentOrder() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 7: Obtenha uma lista de {@link Order}  do dia "15-Mar-2021", Imprima os Valores no Console e devolva a
     * Lista de {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Order> getOrderOfSpecifyDayAndPrintAndReturnProducts() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 8: Calcule o Valor Total de Todos os Pedidos em "Feb 2021"
     */
    public GridView<Order> calValuesOfOrderInFeb2021() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 9: Calcule o valor medio pago nas {@link Order}  de "14-Mar-2021"
     */
    public GridView<Order> getAverageValueInOrderInSpecifyDay() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 11: Obtenha um mapeamento de dados (Map) com o ID da {@link Order} e a quantidade ("count") de
     * {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Order> getMapWithOrderAndQuantityOfProducts() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 12: Produza um mapeamento de dados (Map) com os registros das {@link Order} agrupados pelos
     * {@link Customer}
     */
    public GridView<Order> getMapWithGroupByCustomer() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 13: Produza um mapeamento de dados (Map) com o ID da {@link Order} e o valor total dos
     * {@link com.guilhermepalma.streamexercices.data.model.Product}
     */
    public GridView<Order> getMapWithRegistersAndTotalValue() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 14: Obtenha um mapeamento de dados (Map) com uma Lista de nomes de
     * {@link com.guilhermepalma.streamexercices.data.model.Product} por Categoria
     */
    public GridView<Order> getMapWithProductNameByCategory() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

    /**
     * Exercice 15: Obtenha o {@link com.guilhermepalma.streamexercices.data.model.Product} mais caro de cada Categoria
     */
    public GridView<Order> getProductMoreExpansiveByCategory() {
        List<Order> results = orderRepository.findAll();
        return Util.isNullOrEmpty(results) ? null : new GridView<>(results, Long.valueOf(results.size()));
    }

}
