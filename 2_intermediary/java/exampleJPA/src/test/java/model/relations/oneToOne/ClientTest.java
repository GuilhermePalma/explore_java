package model.relations.oneToOne;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.relations.oneToOne.Client;
import com.guilhermepalma.exampleJPA.model.relations.oneToOne.Position;

public class ClientTest {

    private static final DAO<Object> genericDAO = new DAO<>();

    public static void main(String[] args) {
        insertClient();
    }

    private static void insertClient() {
        try {
            Position position = new Position("5D");
            Client client = new Client("Olies", position);

            // É necessario inserir os dados na Ordem de Dependencia
            genericDAO.registerAtomic(client).close();

        } catch (Exception ex) {
            System.out.println("Exception in Inser Client: \n" + ex.getMessage());
        }
    }

}
