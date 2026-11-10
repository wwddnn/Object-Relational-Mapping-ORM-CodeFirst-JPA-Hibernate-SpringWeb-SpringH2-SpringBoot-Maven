package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // opcao CascadeType.ALL significa as operacoes de persistencia, atualizacao ou exclusao serao propagadas para Payment
    private Payment payment;

    @OneToMany(mappedBy = "id.order") //relacionamento com a classe OrderItem, e que usa o id para ir na classe de associacao OrderItemPK
    private Set<OrderItem> items = new HashSet<>();

    public Order(){
    }

    public Order(Long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() { //metodo get para o relacionamento com Payment
        return payment;
    }

    public void setPayment(Payment payment) { //metodo set para o relacionamento com Payment
        this.payment = payment;
    }

    public Set<OrderItem> getItems() { //metodo get para o relacionamento com OrderItem que vai ate a classe de associacao OrderItemPK
        return items;
    }

    public List<Product> getProducts() { //metodo get para ir nos items e trazer com o getProduct a lista de products que esta na classe de associacao OrderItemPK
        return items.stream().map(x -> x.getProduct()).toList();
    }
}
