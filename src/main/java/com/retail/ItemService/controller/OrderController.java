package com.retail.ItemService.controller;

import com.retail.ItemService.domain.Order;
import com.retail.ItemService.dto.OrderResponse;
import com.retail.ItemService.form.OrderForm;
import com.retail.ItemService.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customers/{customerID}/order")
    public OrderResponse createOrder(@RequestBody @Valid OrderForm form, @PathVariable int customerID) {
        return orderService.createOrder(form, customerID);
    }

    @GetMapping("/customers/{customerID}/order")
    public List<OrderResponse> getAllOrdersByCustomer(@PathVariable int customerID) {
        return orderService.getAllOrderByCustomerID(customerID);
    }
}
