package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.form.customer.CreateCustomerForm;
import com.example.OnlineRetailsystem.repository.AddressRepository;
import com.example.OnlineRetailsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Customer createCustomer(CreateCustomerForm customerForm) {
        Address address = new Address(customerForm.getBillingAddressForm().getStreet(), customerForm.getBillingAddressForm().getCity(), customerForm.getBillingAddressForm().getState(), customerForm.getBillingAddressForm().getZipCode(), AddressType.BILLINGADDRESS);
        addressRepository.save(address);
        Customer customer = new Customer(customerForm.getFirstName(), customerForm.getLastName(), customerForm.getEmail(), address);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(int customerID) {

    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }

    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public List<CreditCard> getCustomerCreditCards(int customerID) {
        return null;
    }

    @Override
    public List<Address> getCustomerAddresses(int customerID) {
        return null;
    }

    @Override
    public List<Order> getCustomerOrders(int customerID) {
        return null;
    }

    @Override
    public CreditCard addCreditCardToCustomer(int customerID, CreditCard creditCard) {
        return null;
    }

    @Override
    public Address addAddressToCustomer(int customerID, Address address) {
        return null;
    }

    @Override
    public Order addOrderToCustomer(int customerID, Order order) {
        return null;
    }

    @Override
    public void deleteCustomerCreditCard(int customerID, CreditCard creditCard) {

    }

    @Override
    public void deleteCustomerAddress(int customerID, Address address) {

    }

    @Override
    public void deleteCustomerOrder(int customerID, Order order) {

    }

    @Override
    public Customer updateCustomerCreditCard(int customerID, CreditCard creditCard) {
        return null;
    }

    @Override
    public Customer updateCustomerAddress(int customerID, Address address) {
        return null;
    }

    @Override
    public Customer updateCustomerOrder(int customerID, Order order) {
        return null;
    }
}
