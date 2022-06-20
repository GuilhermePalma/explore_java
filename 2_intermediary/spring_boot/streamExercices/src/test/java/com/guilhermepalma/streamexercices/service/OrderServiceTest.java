package com.guilhermepalma.streamexercices.service;

import com.guilhermepalma.streamexercices.data.model.Customer;
import com.guilhermepalma.streamexercices.data.model.Order;
import com.guilhermepalma.streamexercices.data.model.Product;
import com.guilhermepalma.streamexercices.data.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

    /**
     * Test Write by gavinklfong and contributor. For see more, go to
     * <a href="https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java">Code Repository - Github</a>
     */

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderService orderService;

    @Test
    void getProductInOrderOfCustomer2() {
        List<Product> result = orderRepo.findAll()
                .stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(o -> o.getProductList().stream())
                .distinct()
                .collect(Collectors.toList());

        assertEquals(result, orderService.getProductInOrderOfCustomer2().getUniqueOrMultipleValues());
    }

    @Test
    void getOrderWithBabyCategory() {
        List<Order> result = orderRepo.findAll()
                .stream()
                .filter(o ->
                        o.getProductList()
                                .stream()
                                .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
                )
                .collect(Collectors.toList());

        assertEquals(result, orderService.getOrderWithBabyCategory().getUniqueOrMultipleValues());
    }

    @Test
    void getTreeRecentOrder() {
        List<Order> result = orderRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        assertEquals(result, orderService.getTreeRecentOrder().getUniqueOrMultipleValues());
    }

    @Test
    void getOrderOfSpecifyDayAndPrintAndReturnProducts() {
        List<Product> result = orderRepo.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(System.out::println)
                .flatMap(o -> o.getProductList().stream())
                .distinct()
                .collect(Collectors.toList());

        assertEquals(result, orderService.getOrderOfSpecifyDayAndPrintAndReturnProducts().getUniqueOrMultipleValues());
    }

    @Test
    void calValuesOfOrderInFeb2021() {
        double result = orderRepo.findAll()
                .stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
                .flatMap(o -> o.getProductList().stream())
                .mapToDouble(Product::getPrice)
                .sum();

        assertEquals(result, orderService.calValuesOfOrderInFeb2021());
    }

    @Test
    void getAverageValueInOrderInSpecifyDay() {
        double result = orderRepo.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .flatMap(o -> o.getProductList().stream())
                .mapToDouble(Product::getPrice)
                .average().orElse(0.0);

        assertEquals(result, orderService.getAverageValueInOrderInSpecifyDay().orElseThrow(IllegalArgumentException::new));
    }

    @Test
    void getMapWithOrderAndQuantityOfProducts() {
        Map<Long, Integer> result = orderRepo.findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                Order::getId,
                                order -> order.getProductList().size()));

        assertEquals(result, orderService.getMapWithOrderAndQuantityOfProducts().getUniqueOrMultipleValues());
    }

    @Test
    void getMapWithGroupByCustomer() {
        Map<Customer, List<Order>> result = orderRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        assertEquals(result, orderService.getMapWithGroupByCustomer().getUniqueOrMultipleValues());
    }

    @Test
    void getMapWithRegistersAndTotalValue() {
        Map<Order, Double> result = orderRepo.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        order -> order.getProductList().stream().mapToDouble(Product::getPrice).sum())
                );

        Map<Long, Double> myRegiters = (Map<Long, Double>) orderService.getMapWithRegistersAndTotalValue().getUniqueOrMultipleValues();

        result.forEach((key, valueSum) -> assertEquals(valueSum, myRegiters.get(key.getId())));
    }
}