package com.example.spring_boot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot3.models.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, String>
{
}
