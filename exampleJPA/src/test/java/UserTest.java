import com.guilhermepalma.exampleJPA.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

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
        updateManageableObject();
        updateNotManageableObject();
        updateUser();

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
     * Metodo Responsavel por executar a Inserção de um Usuario
     */
    private static void insertUser() {
        try {
            // Prepara o EntityManager para Realizar a Inserção
            entityManager.getTransaction().begin();

            User newUser = new User("Luizthe", "luizthe@email.com");

            // Salva um Objeto no Banco de Dados
            entityManager.persist(newUser);

            System.out.println("User Inserted: " + newUser);

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

    /**
     * Metodo Responsavel por Listar todos os {@link User} do Banco de Dados
     */
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

    /**
     * Metodo Padrão para atualizar um {@link User}
     */
    private static void updateUser() {
        try {
            entityManager.getTransaction().begin();

            // Obtem e altera os dados do Usuario
            User user = getUserId(3L) == null ? new User() : getUserId(3L);
            Objects.requireNonNull(user).setName("Miguel");
            Objects.requireNonNull(user).setEmail("miguel@email.com");

            // Pega o Objeto que está no Banco e realiza o Update
            entityManager.merge(user);

            // Finaliza e Executa a manipulação
            entityManager.getTransaction().commit();

            System.out.println("User Changed to: " + user);
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

    /**
     * Realiza uma atualização com um Objeto Gerenciavel: Objeto que está sendo
     * observado pelo JPA e em algum momento o Banco de Dados será sincronizado com ele.
     */
    private static void updateManageableObject() {
        try {
            entityManager.getTransaction().begin();

            // Obtem e altera os dados do Usuario
            User user = getUserId(3L) == null ? new User() : getUserId(3L);
            Objects.requireNonNull(user).setName("Miguel Gerenciavel");
            Objects.requireNonNull(user).setEmail("miguel@email.com");

            // Uma vez que o objeto é gerenciavel, ele é sincronizado por si só
            // entityManager.merge(user);

            // Finaliza e Executa a manipulação
            entityManager.getTransaction().commit();

            System.out.println("User Manageable Changed to: " + user);
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }

    /**
     * Realiza uma atualização a partir de um Objeto Gerenciavel: Objeto
     * que NÃO está sendo observado pelo JPA, sendo necessario chamar
     * explicitamente o metodo para sincronizar.
     */
    private static void updateNotManageableObject() {
        try {
            entityManager.getTransaction().begin();

            // Obtem e altera os dados do Usuario
            User user = getUserId(3L) == null ? new User() : getUserId(3L);
            Objects.requireNonNull(user).setName("Miguel Não Gerenciavel");
            Objects.requireNonNull(user).setEmail("miguel@email.com");

            // Torna o Objeto "User" um Objeto não Gerenciavel. Ou seja, é necessario
            // chamar o "merge" para as Alterações
            entityManager.detach(user);

            // Responsavel por Sincronizar as Alterações do Usuario
            entityManager.merge(user);

            // Finaliza e Executa a manipulação
            entityManager.getTransaction().commit();

            System.out.println("User Not Manageable Changed to: " + user);
        } catch (Exception ex) {
            System.out.println("Exception in List Users: \n" + ex.getMessage());
        }
    }
}
