package com.example.spring_boot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot3.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, String> 
{
}
