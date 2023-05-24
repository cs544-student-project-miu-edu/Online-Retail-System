package com.retail.ItemService.controller;

import com.retail.ItemService.dto.CustomerResponse;
import com.retail.ItemService.form.UpdateCustomerForm;
import com.retail.ItemService.service.CustomerService;
import com.retail.ItemService.form.CreateCustomerForm;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
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
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable int customerId) {
        return customerService.findCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse deleteCustomerById(@PathVariable int customerId) {
        CustomerResponse customerResponse = customerService.findCustomerById(customerId);
        customerService.deleteCustomerByID(customerId);
        return customerResponse;
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerResponse updateCustomer(@PathVariable int customerId, @RequestBody UpdateCustomerForm form) {
        return customerService.updateCustomer(customerId, form);
    }
}
