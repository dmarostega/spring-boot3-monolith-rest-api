package com.example.spring_boot3.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class OrderModel implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client; 

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemModel> orderItems = new ArrayList<OrderItemModel>();
    private Integer number;

    public String getId() 
    {
        return id;
    }

    public Integer getNumber() 
    {
        return number;
    }

    public void setNumber(Integer number) 
    {
        this.number = number;
    }

    public ClientModel getClient() 
    {
        return client;
    }

    public void setClient(ClientModel client) 
    {
        this.client = client;
    }

    public List<OrderItemModel> getOrderItems() 
    {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) 
    {
        this.orderItems = orderItems;
    }    
}
