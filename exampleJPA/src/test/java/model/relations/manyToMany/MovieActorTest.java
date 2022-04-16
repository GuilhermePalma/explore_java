package model.relations.manyToMany;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.relations.manyToMany.Actor;
import com.guilhermepalma.exampleJPA.model.relations.manyToMany.Movie;

import java.util.Arrays;

public class MovieActorTest {

    private static final DAO<Object> genericDAO = new DAO<>();

    public static void main(String[] args) {
        insertMovieActor();
        getMovieById();
        getActorById();

        genericDAO.close();
    }

    /**
     * Insere um Registro de {@link Movie} e {@link Actor} e sua Relação
     */
    private static void insertMovieActor() {
        try {
            Movie movieOne = new Movie("The Increval", 7.0);
            Movie movieTwo = new Movie("The Uncreval", 9.3);
            Movie movieThree = new Movie("The Uva", 8.2);

            Actor actorMarie = new Actor("Marie Rom");
            Actor actorJohn = new Actor("John Lui");
            Actor actorEva = new Actor("Eva Rob");

            movieOne.addActors(Arrays.asList(actorMarie, actorJohn));
            movieTwo.addActors(Arrays.asList(actorMarie, actorJohn, actorEva));
            movieThree.addActor(actorEva);

            genericDAO.registerAtomic(movieOne);
            genericDAO.registerAtomic(movieTwo);
            genericDAO.registerAtomic(movieThree);
        } catch (Exception ex) {
            System.out.println("Exception in Insert Movie/Actor: \n" + ex.getMessage());
        }
    }

    /**
     * Realiza a busca de um {@link Movie} pelo ID
     */
    private static void getMovieById() {
        try {
            System.out.println(new DAO<>(Movie.class).getRegiterById(2L).toStringWithActors());
        } catch (Exception ex) {
            System.out.println("Exception in Get Uncle by ID: \n" + ex.getMessage());
        }
    }

    /**
     * Realiza a busca de um {@link Actor} pelo ID
     */
    private static void getActorById() {
        try {
            System.out.println(new DAO<>(Actor.class).getRegiterById(3L).toStringWithMovies());
        } catch (Exception ex) {
            System.out.println("Exception in Get Uncle by ID: \n" + ex.getMessage());
        }
    }
}
