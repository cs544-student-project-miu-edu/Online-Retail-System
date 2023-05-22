package com.example.OnlineRetailsystem.controller;
//
//import com.example.OnlineRetailsystem.domain.Customer;
//import org.springframework.web.bind.annotation.RequestBody;

import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.form.customer.CreateCustomerForm;
import com.example.OnlineRetailsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//
public class CustomerControllerImpl implements CustomerController {
    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerForm createCustomerForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(createCustomerForm));
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }

    @Override
    public ResponseEntity<?> getCustomer(int customerId) {
        return null;
    }

    @Override
    public List<ResponseEntity<?>> getAllCustomers() {
        return null;
    }

    @Override
    public List<ResponseEntity<?>> getCustomerCreditCards(int customerID) {
        return null;
    }

    @Override
    public List<ResponseEntity<?>> getCustomerAddresses(int customerID) {
        return null;
    }

    @Override
    public List<ResponseEntity<?>> getCustomerOrders(int customerID) {
        return null;
    }
}
