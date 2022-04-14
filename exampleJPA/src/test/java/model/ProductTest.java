package model;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.Product;

import java.util.List;

public class ProductTest {
    private static DAO<Product> productDAO;

    public static void main(String[] args) {
        productDAO = new DAO<>(Product.class);

        insertProduct();
        getAllProducts();
        getFiveProducts();
        updateProduct();
        deleteProducts();
        getProductByID();

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
            System.out.println("Exception in Create Product: \n" + ex.getMessage());
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
            System.out.println("Exception in Products List: \n" + ex.getMessage());
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
            System.out.println("Exception in List Products: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por Realizar a Exclusão do último Usuario Cadastrado
     */
    private static void deleteProducts() {
        try {
            // Obtem o Unico Usuario da Query
            Product product = productDAO.getLastRecord();
            productDAO.deleteAtomic(product);

            System.out.println("Product Exclued: " + product);
        } catch (Exception ex) {
            System.out.println("Exception in Remove Product: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Padrão para atualizar um {@link Product}
     */
    private static void updateProduct() {
        try {
            Product product = productDAO.getLastRecord();
            product.setName("Caneta Azul");
            product.setPrice(8.0);

            productDAO.updateAtomic(product);

            System.out.println("Product Changed to: " + product);
        } catch (Exception ex) {
            System.out.println("Exception in Products List: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por Obter um {@link Product} pelo ID
     */
    private static void getProductByID() {
        try {
            Product product = productDAO.getRegiterById(1L);
            System.out.println("Product Finded: " + product.toString());
        } catch (Exception ex) {
            System.out.println("Exception in Get Product by ID: \n" + ex.getMessage());
        }
    }

}
