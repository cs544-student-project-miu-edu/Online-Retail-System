package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CustomerController {

    @PostMapping("/customers")


    public Customer createCustomer(Customer customer);
//   // public void deleteCustomer(int customerID);
    @PutMapping("/customers")
    public void updateCustomer(int customerId, Customer customer);
    @GetMapping("/customers/{cid}")

    public Customer getCustomer(int customerId);
    @GetMapping("/customers")
    public List<Customer> getAllCustomers();

    @GetMapping("customers/{cid}/creditCards")
    public List<CreditCard> getCustomerCreditCards(int customerID);
    @GetMapping("customers/{cid}/addresses")

    public List<Address> getCustomerAddresses(int customerID);

    @GetMapping("customers/{cid}/orders")

    public List<Order> getCustomerOrders(int customerID);
}
