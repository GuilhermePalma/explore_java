import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.Product;
import com.guilhermepalma.exampleJPA.model.User;

import java.util.List;

public class ProductTest {
    private static DAO<Product> productDAO;

    public static void main(String[] args) {
        productDAO = new DAO<>(Product.class);

        insertProduct();
        getAllProducts();
        getFiveProducts();

        productDAO.close();
    }

    /**
     * Metodo Responsavel por executar a Inserção de um {@link Product}
     */
    private static void insertProduct() {
        try {
            Product product = new Product("Caneta", 6.35);
            productDAO.registerAtomic(product);

            System.out.println("Product Inserted: " + product);
        } catch (Exception ex) {
            System.out.println("Exception in Create User: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por Listar todos os {@link Product} do Banco de Dados
     */
    private static void getAllProducts() {
        try {
            List<Product> listProducts = productDAO.getAllRegisters();

            listProducts.forEach((product -> System.out.println("Product: " + product)));
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por Listar apenas cinco {@link Product} do Banco de Dados
     */
    private static void getFiveProducts() {
        try {
            List<Product> listProducts = productDAO.getAllRegisters(0, 5);

            listProducts.forEach((product -> System.out.println("Product: " + product)));
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

}
