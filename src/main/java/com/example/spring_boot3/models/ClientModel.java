package com.example.spring_boot3.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTS")
public class ClientModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    private String name;
    private Integer docNumber;
    private LocalDate birthday;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<OrderModel> orders;
     
    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Integer getDocNumber() 
    {
        return this.docNumber;
    }

    public void setDocNumber(Integer docNumber) 
    {
        this.docNumber = docNumber;
    }

    public LocalDate getBirthday() 
    {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) 
    {
        this.birthday = birthday;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
