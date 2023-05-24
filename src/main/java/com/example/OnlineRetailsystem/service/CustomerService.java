package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.domain.Order;
import com.example.OnlineRetailsystem.dto.AddressResponse;
import com.example.OnlineRetailsystem.dto.CreditCardResponse;
import com.example.OnlineRetailsystem.dto.CustomerResponse;
import com.example.OnlineRetailsystem.dto.OrderResponse;
import com.example.OnlineRetailsystem.form.customer.CreateCustomerForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerForm customerForm);

    Page<CustomerResponse> getAllCustomers(Pageable pageable);

    CustomerResponse findCustomerById(int customerId);

    CustomerResponse deleteCustomerById(int customerId);

    void updateCustomer(int customerId, Customer customer);

    List<CreditCard> getCustomerCreditCards(int customerId);

    List<Address> getCustomerAddresses(int customerId);

    List<OrderResponse> getCustomerOrders(int customerId);

    CreditCardResponse addCreditCardToCustomer(int customerId, CreditCardResponse creditCard);

    AddressResponse addAddressToCustomer(int customerId, AddressResponse address);

    OrderResponse addOrderToCustomer(int customerId, OrderResponse order);

    void deleteCustomerCreditCard(int customerId, CreditCardResponse creditCard);

    void deleteCustomerAddress(int customerId, AddressResponse address);

    void deleteCustomerOrder(int customerId, OrderResponse order);

    CustomerResponse updateCustomerCreditCard(int customerId, CreditCardResponse creditCard);

    CustomerResponse updateCustomerAddress(int customerId, AddressResponse address);

    CustomerResponse updateCustomerOrder(int customerId, OrderResponse order);
}
