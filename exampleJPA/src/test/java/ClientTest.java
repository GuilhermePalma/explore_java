import com.guilhermepalma.exampleJPA.model.Client;
import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientTest {

    private static final DAO<Object> genericDAO = new DAO<>();

    public static void main(String[] args) {
        insertClient();
    }

    private static void insertClient() {
        try {
            Position position = new Position("18C");
            Client client = new Client("Ana", position);

            // É necessario inserir os dados na Ordem de Dependencia
            genericDAO.openTransaction()
                    .register(position)
                    .register(client)
                    .commitTransaction()
                    .close();

        } catch (Exception ex) {
            System.out.println("Exception in Inser Client: \n" + ex.getMessage());
        }
    }

}
