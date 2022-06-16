package com.guilhermepalma.exampleJPA.model.relations.manyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double rating;

    /**
     * Variavel que não será armazenada no Banco de Dados. Será apenas utilizada apenas para armazenar a Media da
     * Avaliação dos Filmes
     */
    @Transient
    private Double mediaRating;

    /* "joinColumns" = Coluna da Classe responsavel pelo Mapeamento (Movie)
     * "inverseJoinColumns" = Coluna da Classe que possui a segunda parte do Mapeamento (Actor)
     * "referencedColumnName" = Coluna que será usada como Referencia (Ex: Primary Key)
     * "name" = Nome que será aplicado à coluna da Tabela
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id", referencedColumnName = "id"))
    private List<Actor> actorsList;

    public Movie() {
    }

    public Movie(String name, Double rating) {
        super();
        this.name = name;
        this.rating = rating;
    }

    public Movie(Double mediaRating) {
        this.mediaRating = mediaRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getMediaRating() {
        return mediaRating;
    }

    public void setMediaRating(Double mediaRating) {
        this.mediaRating = mediaRating;
    }

    public List<Actor> getActorsList() {
        if (actorsList == null) actorsList = new ArrayList<>();
        return actorsList;
    }

    public void setActorsList(List<Actor> actorsList) {
        this.actorsList = actorsList;
    }

    /**
     * Metodo Responsavel por Implementar a Relação Bidirecional entre um {@link Movie} e um {@link Actor}. Possui
     * validações Internas para evitar a Duplicação de Registros nas Listas ou a Exceção de OBjeto Nulo
     */
    public void addActor(Actor actor) {
        if (actor != null && !getActorsList().contains(actor)) {
            getActorsList().add(actor);
            if (!actor.getMoviesList().contains(this)) actor.getMoviesList().add(this);
        }
    }

    /**
     * Metodo Responsavel por Implementar a Relação Bidirecional entre um {@link Movie} e uma Lista de {@link Actor}.
     * Possui validações Internas para evitar a Duplicação de Registros nas Listas ou a Exceção de Objeto Nulo
     *
     * @see #addActor(Actor)
     */
    public void addActors(List<Actor> actorsList) {
        if (actorsList != null) {
            actorsList.forEach((actor -> addActor(actor)));
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }

    public String toStringWithActors() {
        StringBuilder builderItems = new StringBuilder();
        actorsList.forEach((item -> builderItems.append("\n").append("  ").append(item.toString())));

        return "Movie{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n actorsList=[" + builderItems +
                "\n ]\n}";
    }
}
