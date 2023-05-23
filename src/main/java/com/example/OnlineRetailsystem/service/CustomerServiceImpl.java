package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.customExceptions.NotFoundException;
import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.dto.*;
import com.example.OnlineRetailsystem.form.customer.*;
import com.example.OnlineRetailsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    CreditCardRepository creditCardRepository;



    @Override
    public Customer createCustomer(CreateCustomerForm customerForm) {
        // Map the form data to the Customer object
        Customer customer = mapper.map(customerForm, Customer.class);

        // Map the billing address form data to the Address object
        CreateAddressForm addressForm = customerForm.getBillingAddressForm();
        Address billingAddress = mapper.map(addressForm, Address.class);
        billingAddress.setAddressType(AddressType.BILLINGADDRESS);

        // Set the billing address for the customer
        customer.setAddresses(Collections.singletonList(billingAddress));

        return customerRepository.save(customer);
    }

    //TODO - Implementation - By Meseret
    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {

        return customerRepository.findAll(pageable)
                .map(entity -> mapper.map(entity, CustomerResponse.class));
    }

    @Override
    public CustomerResponse findCustomerById(int countryId) {
        return mapper.map(customerRepository.findById(countryId).get(),CustomerResponse.class);
    }
    @Override
    public CustomerResponse deleteCustomerById(int customerID) {
        CustomerResponse customerResponse = findCustomerById(customerID);
        if (customerResponse != null) {
            customerRepository.deleteById(customerID);
        }
        return customerResponse;
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());

            customerRepository.save(existingCustomer);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }

    @Override
    public List<CreditCard> getCustomerCreditCards(int customerId) {
        return creditCardRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Address> getCustomerAddresses(int customerID) {
        return null;
    }

    @Override
    public List<OrderResponse> getCustomerOrders(int customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CreditCardResponse addCreditCardToCustomer(int customerId, CreditCardResponse creditCard) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CreditCard newCreditCard = mapper.map(creditCard, CreditCard.class);
            customer.getCreditCards().add(newCreditCard);
            customerRepository.save(customer);
            return mapper.map(newCreditCard, CreditCardResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }

    @Override
    public AddressResponse addAddressToCustomer(int customerId, AddressResponse address) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Address newAddress = mapper.map(address, Address.class);
            customer.getShippingAddresses().add(newAddress);
            customerRepository.save(customer);
            return mapper.map(newAddress, AddressResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }


    @Override
    public OrderResponse addOrderToCustomer(int customerId, OrderResponse order) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order newOrder = mapper.map(order, Order.class);
            newOrder.setCustomer(customer); // Set the customer for the new order.
            orderRepository.save(newOrder);
            return mapper.map(newOrder, OrderResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }


    @Override
    public void deleteCustomerCreditCard(int customerId, CreditCardResponse creditCard) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CreditCard existingCard = mapper.map(creditCard, CreditCard.class);
            customer.getCreditCards().remove(existingCard);
            customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }


    @Override
    public void deleteCustomerAddress(int customerId, AddressResponse address) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Address existingAddress = mapper.map(address, Address.class);

            // We need to check if the existing address is the default shipping address
            if (customer.getDefaultShippingAddress() != null && customer.getDefaultShippingAddress().equals(existingAddress)) {
                customer.setDefaultShippingAddress(null);
            }


            customer.getShippingAddresses().remove(existingAddress);
            customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }

    @Override
    public void deleteCustomerOrder(int customerId, OrderResponse order) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order existingOrder = mapper.map(order, Order.class);

            // Remove the order from the customer's orders
            customer.getOrders().removeIf(o -> o.getId() == existingOrder.getId());

            customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }



    @Override
    public CustomerResponse updateCustomerCreditCard(int customerId, CreditCardResponse creditCard) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CreditCard existingCard = mapper.map(creditCard, CreditCard.class);

            customer.getCreditCards().stream()
                    .filter(card -> card.getId() == existingCard.getId())
                    .findFirst()
                    .ifPresent(card -> {
                        card.setCardNumber(existingCard.getCardNumber());
                        card.setExpirationDate(existingCard.getExpirationDate());
                        card.setSecurityCode(existingCard.getSecurityCode());
                    });
            customerRepository.save(customer);
            return mapper.map(customer, CustomerResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }



    @Override
    public CustomerResponse updateCustomerAddress(int customerId, AddressResponse address) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Address existingAddress = mapper.map(address, Address.class);
            // Update the address properties in the customer's list of addresses
            customer.getAddresses().stream()
                    .filter(addr -> addr.getId() == existingAddress.getId())
                    .findFirst()
                    .ifPresent(addr -> {
                        addr.setStreet(existingAddress.getStreet());
                        addr.setCity(existingAddress.getCity());
                        addr.setState(existingAddress.getState());
                        addr.setZipCode(existingAddress.getZipCode());
                        addr.setAddressType(existingAddress.getAddressType());  // Updated line
                    });

            customerRepository.save(customer);
            return mapper.map(customer, CustomerResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }


    @Override
    public CustomerResponse updateCustomerOrder(int customerId, OrderResponse order) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order existingOrder = mapper.map(order, Order.class);
            // Update the order properties in the customer's list of orders
            customer.getOrders().stream()
                    .filter(ord -> ord.getId() == existingOrder.getId())
                    .findFirst()
                    .ifPresent(ord -> {
                        ord.setStatus(existingOrder.getStatus());
                        ord.setLineItems(existingOrder.getLineItems());
                        // You might need to update other properties of the order here
                    });
            customerRepository.save(customer);
            return mapper.map(customer, CustomerResponse.class);
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }

}
