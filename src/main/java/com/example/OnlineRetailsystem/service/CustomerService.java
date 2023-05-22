package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.domain.Customer;

import java.util.List;


public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public void deleteCustomer(int customerID);
    public void updateCustomer(int customerId, Customer customer);
    public Customer getCustomer(int customerId);
    public List<Customer> getAllCustomers();
    public List<CreditCard> getCustomerCreditCards(int customerID);
    public List<Address> getCustomerAddresses(int customerID);
    public List<Order> getCustomerOrders(int customerID);
}
