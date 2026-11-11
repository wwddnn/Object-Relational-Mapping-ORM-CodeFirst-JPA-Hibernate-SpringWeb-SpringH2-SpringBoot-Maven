package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT") //esse campo deve ser um texto com descricao longa e que excede o limite padrao de caracteres
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category", //nome para a nova tabela de juncao criada no banco
        joinColumns = @JoinColumn(name = "product_id"), //nome do campo da tabela de juncao que vai ser a chave estrangeira da tabela Product
        inverseJoinColumns = @JoinColumn(name = "category_id")) //nome do campo da tabela de juncao que vai ser a chave estrangeira da tabela Category
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product") //relacionamento com a classe OrderItem, e que usa o id para ir na classe de associacao OrderItemPK
    private Set<OrderItem> items = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() { //metodo get para o relacionamento com Category
        return categories;
    }

    public Set<OrderItem> getItems() { //metodo get para o relacionamento com OrderItem que vai ate a classe de associacao OrderItemPK
        return items;
    }

    public List<Order> getOrders() { //metodo get para ir nos items e trazer com o getOrder a lista de orders que esta na classe de associacao OrderItemPK
        return items.stream().map(x -> x.getOrder()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
