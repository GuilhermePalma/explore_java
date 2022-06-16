package com.guilhermepalma.examplemvc.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Classe Responsavel por Realizar o Mapeamento de um Product no Banco de Dados
 */
@Entity
@Table(name = "products")
public class Product {

    /**
     * Atributo Identificador da classe, em que o ID será autogerado por meio do "auto-incremment"
     * no Banco de Dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo não nulo e não vazio que define o nome de um Product
     */
    @NotNull
    @NotBlank
    private String name;


    /**
     * Atributo não nulo e Positivo responsavel pelo Preço de um Product
     */
    @NotNull
    @Min(0)
    private Double price;

    /**
     * Atributo Decimal que representa o Desconto que pode ser aplicado a um Product
     */
    @Min(0)
    @Max(1)
    private Double discount;

    public Product() {
    }

    public Product(String name, Double price, Double discount) {
        super();
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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
}
