package com.example.spring_boot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot3.models.OrderItemModel;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, String>
{
}
