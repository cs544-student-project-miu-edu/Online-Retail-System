package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.customExceptions.NotFoundException;
import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.domain.Order;
import com.example.OnlineRetailsystem.dto.AddressResponse;
import com.example.OnlineRetailsystem.dto.CreditCardResponse;
import com.example.OnlineRetailsystem.dto.CustomerResponse;
import com.example.OnlineRetailsystem.dto.OrderResponse;
import com.example.OnlineRetailsystem.form.customer.CreateCustomerForm;
import com.example.OnlineRetailsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RequestMapping("api/customers")
@RestController
public class CustomerController {

    @Autowired
    ModelMapper mapper;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerForm form) {
        return customerService.createCustomer(form);
    }

    @GetMapping
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable int customerId) {
        return customerService.findCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}")
    public CustomerResponse deleteCustomerById(@PathVariable int customerId) {
        CustomerResponse customerResponse = customerService.deleteCustomerById(customerId);
        if (customerResponse == null) {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }
        return customerResponse;
    }


    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        customerService.updateCustomer(customerId, customer);
    }

    @GetMapping("/{cid}/creditCards")
    public List<ResponseEntity<CustomerResponse>> getCustomerCreditCards(@PathVariable int cid) {
        CustomerResponse customerResponse = customerService.findCustomerById(cid);
        if (customerResponse != null) {
            List<CreditCard> creditCards = customerService.getCustomerCreditCards(cid);
            List<CreditCardResponse> creditCardResponses = creditCards.stream()
                    .map(creditCard -> mapper.map(creditCard, CreditCardResponse.class))
                    .collect(Collectors.toList());

            List<ResponseEntity<CustomerResponse>> responseEntities = creditCardResponses.stream()
                    .map(creditCardResponse -> {
                        CustomerResponse response = new CustomerResponse();
                        response.getCreditCards().add(creditCardResponse);
                        return ResponseEntity.ok(response);
                    })
                    .collect(Collectors.toList());

            return responseEntities;
        } else {
            throw new NotFoundException("Customer not found with ID: " + cid);
        }
    }

    @GetMapping("/{cid}/addresses")
    public List<ResponseEntity<AddressResponse>> getCustomerAddresses(@PathVariable int customerId) {
        List<Address> addresses = customerService.getCustomerAddresses(customerId);

        List<AddressResponse> addressResponses = addresses.stream()
                .map(address -> mapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());

        List<ResponseEntity<AddressResponse>> responseEntities = addressResponses.stream()
                .map(addressResponse -> ResponseEntity.ok().body(addressResponse))
                .collect(Collectors.toList());

        return responseEntities;
    }


    @GetMapping("/{cid}/orders")
    public List<ResponseEntity<OrderResponse>> getCustomerOrders(@PathVariable int customerId) {
        List<OrderResponse> orders = customerService.getCustomerOrders(customerId);

        List<ResponseEntity<OrderResponse>> responseEntities = orders.stream()
                .map(order -> ResponseEntity.ok().body(mapper.map(order, OrderResponse.class)))
                .collect(Collectors.toList());

        return responseEntities;
    }

}
