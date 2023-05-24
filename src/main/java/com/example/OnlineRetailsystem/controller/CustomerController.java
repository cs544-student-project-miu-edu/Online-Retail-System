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
import com.example.OnlineRetailsystem.service.CreditCardService;
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

//    @Autowired
//    CreditCardService creditCardService;

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
    public ResponseEntity<List<CreditCard>> getCustomerCreditCards(@PathVariable("cid") int customerId) {
        List<CreditCard> creditCards = customerService.getCustomerCreditCards(customerId);
        return ResponseEntity.ok(creditCards);
    }

//    @GetMapping("/{customerId}/creditCard/{cardId}")
//    @ResponseStatus(HttpStatus.OK)
//    public CreditCardResponse getCustomersCreditCard(@PathVariable int customerId, @PathVariable int cardId) {
//        return mapper.map(creditCardService.getCustomersCreditCard(customerId, cardId), CreditCardResponse.class);
//    }

//    @DeleteMapping("/customers/{customerID}/creditCard/{cardID}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteCreditCard(@PathVariable int customerID, @PathVariable int cardID) {
//        creditCardService.deleteCreditCard(customerID, cardID);
//    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<List<Address>> getCustomerAddresses(@PathVariable("customerId") int customerId) {
        List<Address> addresses = customerService.getCustomerAddresses(customerId);
        return ResponseEntity.ok(addresses);
    }

    //TODO - need to implement AddressService
    @GetMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<Address> getCustomerAddress(@PathVariable("customerId") int customerId, @PathVariable("addressId") int addressId) {
        Address address = customerService.getCustomerAddress(customerId, addressId);
        if (address == null) {
            throw new NotFoundException("Address not found for customer with ID: " + customerId + " and address ID: " + addressId);
        }
        return ResponseEntity.ok(address);
    }

    //TODO - need to implement orderService
    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderResponse>> getCustomerOrders(@PathVariable("customerId") int customerId) {
        List<OrderResponse> orders = customerService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }

}
