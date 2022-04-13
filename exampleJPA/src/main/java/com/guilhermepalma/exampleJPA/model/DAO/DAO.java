package com.guilhermepalma.exampleJPA.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Criação de uma Classe Generica. Ela é responsavel por possuir os metodos
 * que são utilizados no Banco de Dados.
 */
public class DAO<E> {

    /**
     * Variavel Constante definida para Obter todos os Registros da Tabela
     */
    private static final int ALL_REGISTER = -1;

    /**
     * Responsavel pela Criação e Configuração de um EntityManager. Ele
     * obtem as Configurações do JPA e da Conexão com o Banco de Dados
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Responsavel pela manipulação das Classes Mapeadas pelo JPA no
     * Banco de Dados. Já inclui uma conexão especifica com o Banco de Dados.
     */
    private static EntityManager entityManager;

    /**
     * Metodo Chamado Apenas uma unica vez quando a Classe é Inicializada
     */
    static {
        try {
            // Cria uma EntityManagerFactory com as Configurações da Unidade "example_jpa"
            entityManagerFactory = Persistence.createEntityManagerFactory("example_jpa");
        } catch (Exception ex) {
            System.out.println("Exception in Entity Factory: \n" + ex.getMessage());
        }
    }

    /**
     * Variavel que referencia a Classe do Banco de Dados que será manipulada
     */
    private final Class<E> referenceClass;

    /**
     * Construtor da Classe que cria um EntityManager carregando as configurações e
     * obtem a Classe que será manipulada no Banco de Dados
     */
    public DAO(Class<E> referenceClass) {
        entityManager = entityManagerFactory.createEntityManager();
        this.referenceClass = referenceClass;
    }

    /**
     * Construtor com a Class que será usada nula
     */
    public DAO() {
        this(null);
    }

    /**
     * Metodo Encadeado (Retorna uma instância da Propria Classe) reponsavel
     * por abrir uma Transação do Banco de Dados
     *
     * @return {@link DAO Instancia da Propria Classe DAO}
     */
    public DAO<E> openTransaction() {
        entityManager.getTransaction().begin();
        return this;
    }

    /**
     * Metodo Encadeado (Retorna uma instância da Propria Classe) reponsavel
     * por Fechar a Transação e Realizar as Alterações do Banco de Dados
     *
     * @return {@link DAO Instancia da Propria Classe DAO}
     */
    public DAO<E> commitTransaction() {
        entityManager.getTransaction().commit();
        return this;
    }

    /**
     * Metodo Encadeado (Retorna uma instância da Propria Classe) reponsavel
     * por Realizar um Cadastro de um Novo Dado no Banco de Dados
     *
     * @return {@link DAO Instancia da Propria Classe DAO}
     */
    public DAO<E> register(E entity) {
        entityManager.persist(entity);
        return this;
    }

    /**
     * Realiza o Cadastro Completo da {@link E Classe Generica}
     *
     * @return {@link DAO Instancia da Propria Classe DAO}
     */
    public DAO<E> registerAtomic(E entity) {
        return this.openTransaction().register(entity).commitTransaction();
    }

    /**
     * Obtem uma lista de registro da Tabela com alguns filtros
     *
     * @param initialRegister Posição da qual será obtida o Primeiro Registro
     * @param maxRegister     Quantidade Maxima de Registros Obtidos
     * @return {@link List List da Classe passada na Instancia}
     * @see #ALL_REGISTER
     */
    public List<E> getAllRegisters(int initialRegister, int maxRegister) {
        if (referenceClass == null) {
            throw new UnsupportedOperationException("Operation not Supported in Null Class");
        }

        String jpql = "SELECT e FROM " + referenceClass.getName() + " e";
        TypedQuery<E> query = entityManager.createQuery(jpql, referenceClass);

        if (initialRegister != ALL_REGISTER) query.setFirstResult(initialRegister);
        if (maxRegister != ALL_REGISTER) query.setMaxResults(maxRegister);

        return query.getResultList();
    }

    /**
     * Obtem uma Lista com todos os Registros do Banco de Dados
     */
    public List<E> getAllRegisters() {
        return getAllRegisters(ALL_REGISTER, ALL_REGISTER);
    }

    /**
     * Responsavel por Fechar a Conexão do EntityManager utilizado
     */
    public void close() {
        // Fecha apenas o EntityManager que foi utilizado
        entityManager.close();
    }

}
