import com.guilhermepalma.exampleJPA.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Classe Responsavel por realizar os Testes nas Operações do Banco
 * de Dados com os Usuarios
 */
public class UserTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        initializeEntity();

        insertUser();
        getUserId(2L);
        getAllUsers();
        updateUser(2L);

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void initializeEntity() {
        try {
            // EntityManagerFactory: Responsavel pela Criação de um Entity Manager.
            // createEntityManagerFactory(name): Cria um EntityManager com as Configurações com o nome informado
            entityManagerFactory = Persistence.createEntityManagerFactory("example_jpa");

            // EntityManager: Responsavel pela manipulação das Classes Mapeadas pelo JPA
            // no Banco de Dados. Já inclui uma conexão especifica com o Banco de Dados
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception ex) {
            System.out.println("Exception in Enntity User: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por validar a Inserção de um Usuario
     */
    private static void insertUser() {
        try {
            // Prepara o EntityManager para Realizar a Inserção
            entityManager.getTransaction().begin();

            User newUser = new User("Luizthe", "luizthe@email.com");

            // Salva um Objeto no Banco de Dados
            entityManager.persist(newUser);

            System.out.println("User Inserted: " + newUser.toString());

            // Finaliza a Operação com o Banco de Dados
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception in Create User: \n" + ex.getMessage());
        }
    }

    /**
     * Metodo Responsavel por Obter os Usuarios pelo ID
     */
    private static User getUserId(Long id) {
        try {
            // .find(classe mapeada que será obtida, tipo de dado buscado)
            User user = entityManager.find(User.class, id);

            System.out.println("User Finded: " + user.toString());
            return user;
        } catch (Exception ex) {
            System.out.println("Exception in Get User by ID: \n" + ex.getMessage());
            return null;
        }
    }

    private static void getAllUsers() {
        try {
            // Obtem todos os Usuarios com todos os seus atributos (colunas)
            String jpql = "SELECT u FROM User u";

            TypedQuery<User> queryUser = entityManager.createQuery(jpql, User.class);
            queryUser.setMaxResults(3);

            // Efetiva a consulta e Obtem apenas os 3 Usarios
            List<User> listUsers = queryUser.getResultList();

            listUsers.forEach((user) -> System.out.println("User List: " + user.toString()));

        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

    private static void updateUser(Long id){
        try {
            entityManager.getTransaction().begin();

            // Obtem e altera os dados do Usuario
            User user = getUserId(id) == null ? new User() : getUserId(id);
            user.setName("Miguel");
            user.setEmail("miguel@email.com");

            // Pega o Objeto que está no Banco e realiza o Update
            entityManager.merge(user);

            // Finaliza e Executa a manipulação
            entityManager.getTransaction().commit();

            System.out.println("User Changed to: " + user.toString());
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

}
