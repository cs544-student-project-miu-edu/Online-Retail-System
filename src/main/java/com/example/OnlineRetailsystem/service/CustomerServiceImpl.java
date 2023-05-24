package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.customExceptions.NotFoundException;
import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.dto.*;
import com.example.OnlineRetailsystem.form.customer.*;
import com.example.OnlineRetailsystem.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ModelMapper mapper;
    //For testing
    public CustomerServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AddressRepository addressRepository;

    //@Transactional
    public CustomerResponse createCustomer(CreateCustomerForm customer) {
        Address billingAddress = new Address(customer.getBillingAddressForm().getStreet(), customer.getBillingAddressForm().getCity(), customer.getBillingAddressForm().getState(), customer.getBillingAddressForm().getZipCode(), AddressType.BILLINGADDRESS);
        Address defaultShippingAddress = new Address(customer.getBillingAddressForm().getStreet(), customer.getBillingAddressForm().getCity(), customer.getBillingAddressForm().getState(), customer.getBillingAddressForm().getZipCode(), AddressType.SHIPPINGADDRESS);
        Customer newCustomer = new Customer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), billingAddress, defaultShippingAddress);
        Customer createdCustomer = customerRepository.save(newCustomer);
        return mapper.map(createdCustomer, CustomerResponse.class);
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {

        return customerRepository.findAll(pageable)
                .map(entity -> mapper.map(entity, CustomerResponse.class));
    }

    @Override
    public CustomerResponse findCustomerById(int customerId) {
        return mapper.map(customerRepository.findById(customerId).get(),CustomerResponse.class);
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
        return creditCardRepository.findCreditCardsByCustomerId(customerId);
    }


    @Override
    public List<Address> getCustomerAddresses(int customerId) {
        return addressRepository.findAddressByCustomerId(customerId);
    }


    @Override
    public List<OrderResponse> getCustomerOrders(int customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }
    // TODO - implementation on AddressService
    @Override
    public Address getCustomerAddress(int customerId, int addressId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));

        List<Address> addresses = customer.getAddresses();
        for (Address address : addresses) {
            if (address.getId() == addressId) {
                return address;
            }
        }

        throw new NotFoundException("Address not found for customer with ID: " + customerId + " and address ID: " + addressId);
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

            // Check if the existing address is the default shipping address
            if (customer.getDefaultShippingAddress() != null && customer.getDefaultShippingAddress().equals(existingAddress)) {
                // Remove the existing address from the list of shipping addresses
                customer.getShippingAddresses().remove(existingAddress);

                // Set a new default shipping address if there are other addresses available
                if (!customer.getShippingAddresses().isEmpty()) {
                    Address newDefaultAddress = customer.getShippingAddresses().get(0);
                    customer.setDefaultShippingAddress(newDefaultAddress.getId());
                } else {
                    // If there are no other addresses, set the default shipping address to null
                    customer.setDefaultShippingAddress(null);
                }
            } else {
                // If the existing address is not the default, simply remove it from the list of shipping addresses
                customer.getShippingAddresses().remove(existingAddress);
            }

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

            // Find the order in the customer's list of orders
            Optional<Order> orderOptional = customer.getOrders().stream()
                    .filter(ord -> ord.getId() == existingOrder.getId())
                    .findFirst();

            if (orderOptional.isPresent()) {
                Order orderToUpdate = orderOptional.get();
                orderToUpdate.setState(existingOrder.getState());
                orderToUpdate.setLineItems(mapper.map(order.getLineItems(), new TypeToken<List<ItemLine>>() {}.getType()));
                // You might need to update other properties of the order here

                customerRepository.save(customer);
                return mapper.map(customer, CustomerResponse.class);
            } else {
                throw new NotFoundException("Order not found with ID: " + existingOrder.getId());
            }
        } else {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
    }


}
