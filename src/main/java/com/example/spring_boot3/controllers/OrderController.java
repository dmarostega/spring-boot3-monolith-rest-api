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

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> list()
    {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }

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

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") String id) {
        Optional<OrderModel> order = orderRepository.findById(id);

        if(order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(order.get());
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String id,
                                             @RequestBody @Valid OrderRecord orderRecord) {
        Optional<OrderModel> order = orderRepository.findById(id);
        
        if(order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }

        var orderModel = order.get();

        if(!orderRecord.client_id().equals(order.get().getClient().getId())){
            Optional<ClientModel> client = clientRepository.findById(orderRecord.client_id());
            if (client.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found.");
            }
            orderModel.setClient(client.get());
        }

        BeanUtils.copyProperties(orderRecord, orderModel);

        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(orderModel));
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String id)
    {
        Optional<OrderModel> order = orderRepository.findById(id);
        
        if(order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        orderRepository.delete(order.get());

        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }
}
