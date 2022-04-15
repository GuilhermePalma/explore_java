package com.guilhermepalma.exampleJPA.model.relations.manyToOne;

import com.guilhermepalma.exampleJPA.model.Product;
import com.guilhermepalma.exampleJPA.model.relations.oneToMany.Order;

import javax.persistence.*;

@Entity(name = "item_oder")
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    /**
     * Registra o Preço aplicado do Produto nessa Venda
     */
    @Column(nullable = false)
    private Double price;

    // Muitos Itens de Pedido para um Pedido
    // Usa o "cascade=CascadeType.ALL" para fazer as operações CRUD em Conjunto
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    // Um Item do Pedido está relacionado à apenas um Produto,
    // mas um Produto pode ser referenciado em varios Itens de Pedidos
    /**
     * Define as caracteristicas do Item que está sendo comprado
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public ItemOrder() {
    }

    public ItemOrder(Order order, Product product, Integer quantity) {
        super();
        this.setQuantity(quantity);
        this.setOrder(order);
        this.setProduct(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;

        // Obtem o Preço atual do Produto na Hora em que o Produto é Informado
        if (product != null && this.price == null) {
            this.setPrice(product.getPrice());
        }
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "id=" + id.toString() +
                ", quantity=" + quantity.toString() +
                ", price=" + price.toString()+
                ", product=" + product.toString() +
                "}";
    }
}
