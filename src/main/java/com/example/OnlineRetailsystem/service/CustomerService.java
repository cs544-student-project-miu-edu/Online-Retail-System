package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public void deleteCustomerById(int customerID);
    public void updateCustomer(int customerId, Customer customer);
    public Customer getCustomer(int customerId);
    public List<Customer> getAllCustomers();

    public List<CreditCard> getCustomerCreditCards(int customerID);
    public List<Address> getCustomerAddresses(int customerID);
    public List<Order> getCustomerOrders(int customerID);
    public CreditCard addCreditCardToCustomer(int customerID, CreditCard creditCard);
    public Address addAddressToCustomer(int customerID, Address address);
    public  Order addOrderToCustomer(int customerID, Order order);

    public void deleteCustomerCreditCard(int customerID, CreditCard creditCard);
    public void deleteCustomerAddress(int customerID, Address address);
    public void deleteCustomerOrder(int customerID, Order order);

    public Customer updateCustomerCreditCard(int customerID, CreditCard creditCard);
    public Customer updateCustomerAddress(int customerID, Address address);
    public Customer updateCustomerOrder(int customerID, Order order);


}
