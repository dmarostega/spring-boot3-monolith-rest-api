package com.example.spring_boot3.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class ProductModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * UUID incompativel com MySql
     */
    @Id
    // @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)") 
    private String id = UUID.randomUUID().toString();
    private String name;
    private BigDecimal value;

    public String getId() 
    {
        return this.id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getValue() 
    {
        return value;
    }
    
    public void setValue(BigDecimal value) 
    {
        this.value = value;
    }
}
