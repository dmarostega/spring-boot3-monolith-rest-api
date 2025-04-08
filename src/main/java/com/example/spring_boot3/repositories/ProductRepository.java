package com.example.spring_boot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot3.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {
}
