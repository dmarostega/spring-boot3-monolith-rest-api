package com.example.spring_boot3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient.ResponseSpec;

import com.example.spring_boot3.dtos.ClientRecordDto;
import com.example.spring_boot3.dtos.ProductRecordDto;
import com.example.spring_boot3.models.ClientModel;
import com.example.spring_boot3.models.ProductModel;
import com.example.spring_boot3.repositories.ClientRepository;

import jakarta.validation.Valid;

@RestController
public class ClientController 
{

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> list()
    {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientModel> getById(@PathVariable(value="id") String id) 
    {
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client.get());    
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> save(@RequestBody @Valid ClientRecordDto clientRecordDto)
    {
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") String id, 
                                         @RequestBody @Valid ClientRecordDto clientRecordDto)
    {
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var clientModel = client.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));    
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String id)
    {
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        clientRepository.delete(client.get());        
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }
    
}
