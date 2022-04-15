package com.guilhermepalma.exampleJPA.model.relations.oneToMany;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Temporal informa o que o dado se trata de uma Data
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /* A Propriedade "mappedBy" é usada para referenciar a variavel da outra classe.
     * Por padrão, os ItemOrder não são obtidos. Eles só são obtidos quando utiliza do
     * getItemsOrder() que resulta em uma Segunda Consulta no Banco de Dados.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemOrder> itemsOrder;

    public Order() {
        this(Date.from(Instant.from(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")))));
    }

    public Order(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ItemOrder> getItemsOrder() {
        return itemsOrder;
    }

    public void setItemsOrder(List<ItemOrder> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }

    @Override
    public String toString() {
        StringBuilder builderItems = new StringBuilder();
        itemsOrder.forEach((item -> builderItems.append("\n").append("  ").append(item.toString())));

        return "Order{" + "\n id=" + id + ",\n date=" + date.toString() + ",\n itemsOrder=[" + builderItems + "\n ]" + "\n}";
    }
}
