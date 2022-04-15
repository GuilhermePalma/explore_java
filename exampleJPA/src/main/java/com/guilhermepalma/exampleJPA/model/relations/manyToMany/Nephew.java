package com.guilhermepalma.exampleJPA.model.relations.manyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o Registro de Sobrinhos no Banco de Dados
 */
@Entity
@Table(name = "nephews")
public class Nephew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /* Representa um Relacionamento Muitos p/ Muitos, em que nessa classe aponta para a variavel "nephews"
     * da Classe "Uncle" onde é feito o Mapeamento dessa Relação */
    @ManyToMany(mappedBy = "nephewsList", cascade = CascadeType.ALL)
    private List<Uncle> unclesList = new ArrayList<>();

    public Nephew(String name) {
        super();
        this.name = name;
    }

    public Nephew() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Uncle> getUnclesList() {
        return unclesList;
    }

    public void setUnclesList(List<Uncle> unclesList) {
        this.unclesList = unclesList;
    }

    @Override
    public String toString() {
        return "Nephew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}";
    }

    public String toStringWithUncles() {
        StringBuilder builderItems = new StringBuilder();
        unclesList.forEach((item -> builderItems.append("\n").append("  ").append(item.toString())));

        return "Nephew{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n unclesList=[" + builderItems +
                "\n ]\n}";
    }
}
