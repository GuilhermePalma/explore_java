package com.guilhermepalma.exampleJPA.model.relations.manyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o Registro de Tios no Banco de Dados
 */
@Entity
@Table(name = "uncles")
public class Uncle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /* Representa um Relacionamento Muitos p/ Muitos, em que nessa classe é feito o mapeamento do relacionamento */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Nephew> nephewsList = new ArrayList<>();

    public Uncle(String name) {
        super();
        this.name = name;
    }

    public Uncle() {
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

    public List<Nephew> getNephewsList() {
        return nephewsList;
    }

    public void setNephewsList(List<Nephew> nephewsList) {
        this.nephewsList = nephewsList;
    }

    public String toStringWithNephews() {
        StringBuilder builderItems = new StringBuilder();
        nephewsList.forEach((item -> builderItems.append("\n").append("  ").append(item.toString())));

        return "Uncle{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n nephewsList=[" + builderItems +
                "\n ]\n}";
    }

    @Override
    public String toString() {
        return "Uncle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}";
    }
}

