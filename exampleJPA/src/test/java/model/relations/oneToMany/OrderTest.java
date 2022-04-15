package model.relations.oneToMany;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.Product;
import com.guilhermepalma.exampleJPA.model.relations.oneToMany.ItemOrder;
import com.guilhermepalma.exampleJPA.model.relations.oneToMany.Order;

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
            Product product = new Product("Atum", 3.25);
            ItemOrder itemOrder = new ItemOrder(order, product, 5);

            genericDAO.registerAtomic(itemOrder);

            System.out.println("Order Inserted: " + order.toString());
        } catch (Exception ex) {
            System.out.println("Exception in Get Order by ID: \n" + ex.getMessage());
        }
    }

    private static void getOrder() {
        try {
            Order order = new DAO<>(Order.class).getRegiterById(1L);
            System.out.println("Order: " + order.toString());
        } catch (Exception ex) {
            System.out.println("Exception in Get Order by ID: \n" + ex.getMessage());
        }
    }
}
