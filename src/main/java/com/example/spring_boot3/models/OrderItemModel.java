package com.example.spring_boot3.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name="order_id", nullable = false)
    private OrderModel order;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    private Integer quantity;

    public String getId()
    {
        return id;
    }

    public OrderModel getOrder()
    {
        return order;
    }

    public void setOrder(OrderModel order)
    {
        this.order = order;
    }

    public ProductModel getProduct()
    {
        return product;
    }

    public void setProduct(ProductModel product)
    {
        this.product = product;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}
