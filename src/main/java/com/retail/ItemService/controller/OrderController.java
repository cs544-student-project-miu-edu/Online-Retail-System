package com.retail.ItemService.controller;

import com.retail.ItemService.domain.Order;
import com.retail.ItemService.dto.OrderResponse;
import com.retail.ItemService.form.OrderForm;
import com.retail.ItemService.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customers/{customerID}/order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid OrderForm form, @PathVariable int customerID) {
        return orderService.createOrder(form, customerID);
    }

    @GetMapping("/customers/{customerID}/order")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrdersByCustomer(@PathVariable int customerID) {
        return orderService.getAllOrderByCustomerID(customerID);
    }

    @PutMapping("/customers/{customerID}/order")
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(@PathVariable int customerID) {
        orderService.placeOrder(customerID);
    }

    @DeleteMapping("/customers/{customerID}/order")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable int customerID) {
        orderService.deleteOrder(customerID);
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/order/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderByID(@PathVariable int orderID) {
        return orderService.getOrderByIDwithDto(orderID);
    }
}
