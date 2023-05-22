package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CustomerController {

    @PostMapping("/customers")


    public ResponseEntity<?> createCustomer(Customer customer);
//   // public void deleteCustomer(int customerID);
    @PutMapping("/customers")
    public void updateCustomer(int customerId, Customer customer);
    @GetMapping("/customers/{cid}")

    public ResponseEntity<?> getCustomer(int customerId);
    @GetMapping("/customers")
    public List<ResponseEntity<?>> getAllCustomers();

    @GetMapping("customers/{cid}/creditCards")
    public List<ResponseEntity<?>> getCustomerCreditCards(int customerID);
    @GetMapping("customers/{cid}/addresses")

    public List<ResponseEntity<?>> getCustomerAddresses(int customerID);

    @GetMapping("customers/{cid}/orders")

    public List<ResponseEntity<?>> getCustomerOrders(int customerID);
}
