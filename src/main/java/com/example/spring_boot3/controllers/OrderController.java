package com.example.spring_boot3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot3.dtos.OrderItemRecordDto;
import com.example.spring_boot3.dtos.OrderRecord;
import com.example.spring_boot3.models.ClientModel;
import com.example.spring_boot3.models.OrderItemModel;
import com.example.spring_boot3.models.OrderModel;
import com.example.spring_boot3.models.ProductModel;
import com.example.spring_boot3.repositories.ClientRepository;
import com.example.spring_boot3.repositories.OrderRepository;
import com.example.spring_boot3.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired 
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/orders")
    public ResponseEntity<OrderModel> save(@RequestBody @Valid OrderRecord orderRecord)
    {
        var order = new OrderModel();
        ClientModel client = clientRepository.findById(orderRecord.client_id())
                        .orElseThrow(() -> new RuntimeException("Client not found."));
            
        order.setClient(client);
        order.setNumber(orderRecord.number());

        for(OrderItemRecordDto itemDTO : orderRecord.orderItems()) {
            ProductModel product = productRepository.findById(itemDTO.product_id()) 
                         .orElseThrow(() -> new RuntimeException("Product not found."));
            var orderItem = new OrderItemModel();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());

            order.getOrderItems().add(orderItem);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }
}
