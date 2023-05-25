package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Order;
import com.example.OnlineRetailsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<?> getOrderByID(@PathVariable int orderID) {
        Order order = orderService.getOrderByID(orderID);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
