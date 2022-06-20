package model.relations.manyToMany;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.relations.manyToMany.Nephew;
import com.guilhermepalma.exampleJPA.model.relations.manyToMany.Uncle;

import java.util.Arrays;

public class UncleNephewTest {

    private static final DAO<Object> genericDAO = new DAO();

    public static void main(String[] args) {

        insertUncleNephew();
        getUncleById();
        getNephewById();

        genericDAO.close();
    }

    /**
     * Insere um Registro de {@link Uncle} e {@link Nephew} e sua Relação
     */
    private static void insertUncleNephew() {
        try {
            Uncle uncleJorge = new Uncle("Jorge");
            Uncle uncleMaria = new Uncle("Maria");
            Uncle uncleJoao = new Uncle("João");

            Nephew nephewJunior = new Nephew("Junior");
            Nephew nephewAnna = new Nephew("Anna");

            uncleJorge.getNephewsList().addAll(Arrays.asList(nephewJunior, nephewAnna));
            uncleMaria.getNephewsList().add(nephewAnna);
            uncleJoao.getNephewsList().add(nephewAnna);

            nephewJunior.getUnclesList().add(uncleJorge);
            nephewAnna.getUnclesList().addAll(Arrays.asList(uncleJorge, uncleMaria, uncleJoao));

            genericDAO.registerAtomic(nephewAnna);
            genericDAO.registerAtomic(nephewJunior);
        } catch (Exception ex) {
            System.out.println("Exception in Insert Uncle/Nephew: \n" + ex.getMessage());
        }
    }


    /**
     * Realiza a busca de um {@link Uncle} pelo ID
     */
    private static void getUncleById() {
        try {
            System.out.println(new DAO<>(Uncle.class).getRegiterById(1L).toStringWithNephews());
        } catch (Exception ex) {
            System.out.println("Exception in Get Uncle by ID: \n" + ex.getMessage());
        }
    }

    /**
     * Realiza a busca de um {@link Nephew} pelo ID
     */
    private static void getNephewById() {
        try {
            System.out.println(new DAO<>(Nephew.class).getRegiterById(1L).toStringWithUncles());
        } catch (Exception ex) {
            System.out.println("Exception in Get Nephew by ID: \n" + ex.getMessage());
        }
    }

}
