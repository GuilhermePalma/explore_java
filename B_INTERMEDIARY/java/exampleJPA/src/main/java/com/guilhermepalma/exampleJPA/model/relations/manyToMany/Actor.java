package com.guilhermepalma.exampleJPA.model.relations.manyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "actorsList", cascade = CascadeType.ALL)
    private List<Movie> moviesList;

    public Actor() {
    }

    public Actor(String name) {
        super();
        this.name = name;
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

    public List<Movie> getMoviesList() {
        if (moviesList == null) moviesList = new ArrayList<>();
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public String toStringWithMovies() {
        StringBuilder builderItems = new StringBuilder();
        moviesList.forEach((item -> builderItems.append("\n").append("  ").append(item.toString())));

        return "Actor{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n moviesList=[" + builderItems +
                "\n ]\n}";
    }
}
