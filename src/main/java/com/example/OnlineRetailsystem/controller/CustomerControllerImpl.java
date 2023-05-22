package com.example.OnlineRetailsystem.controller;
//
//import com.example.OnlineRetailsystem.domain.Customer;
//import org.springframework.web.bind.annotation.RequestBody;
import com.example.OnlineRetailsystem.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@RestController
public class CustomerControllerImpl implements CustomerController {

    @Override
    public ResponseEntity<?> createCustomer(Customer customer) {
        return null;
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
