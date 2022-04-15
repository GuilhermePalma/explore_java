package model.relations.oneToMany;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.Product;
import com.guilhermepalma.exampleJPA.model.relations.manyToOne.ItemOrder;
import com.guilhermepalma.exampleJPA.model.relations.oneToMany.Order;

import java.util.Arrays;

public class OrderTest {

    private static DAO<Object> genericDAO;

    public static void main(String[] args) {
        genericDAO = new DAO<>();

        insertOrder();
        getOrder();

        genericDAO.close();
    }

    private static void insertOrder() {
        try {
            Order order = new Order();

            ItemOrder itemOrderOne = new ItemOrder(order, new Product("Atum", 3.25), 5);
            ItemOrder itemOrderTwo = new ItemOrder(order, new Product("Cerveja", 7.90), 3);
            ItemOrder itemOrderThree = new ItemOrder(order, new Product("Biscoito Doce", 6.5), 10);
            order.setItemsOrder(Arrays.asList(itemOrderOne, itemOrderTwo, itemOrderThree));

            genericDAO.registerAtomic(order);

            System.out.println("Order Inserted: " + order);
        } catch (Exception ex) {
            System.out.println("Exception in Insert Order by ID: \n" + ex.getMessage() + "\n StackTrace:\n" +
                    Arrays.toString(ex.getStackTrace()));
        }
    }

    private static void getOrder() {
        try {
            Order order = new DAO<>(Order.class).getRegiterById(1L);

            /* Caso o EntityManager estivesse sido fechado nessa linha, não seria possivel mapear os
             * ItemOrder, já que foram obtidos somente em uma segunda consulta */
            System.out.println("Order: " + order.toString());
        } catch (Exception ex) {
            System.out.println("Exception in Get Order by ID: \n" + ex.getMessage());
        }
    }
}
