package com.devsuperior.dscommerce.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable //no JPA usamos a anotacao @Embeddable que significa que a chave composta pode ser embutida em outra entidade
public class OrderItemPK {

    @ManyToOne //significa que muitos itens de pedido podem estar associados a um pedido
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne //significa que muitos itens de pedido podem estar associados a um produto
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItemPK() {
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
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(order);
    }
}
