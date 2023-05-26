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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    ModelMapper mapper;
    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable int customerID) {
        return customerService.findCustomerById(customerID);
    }

    @DeleteMapping("/{customerID}")
    @PreAuthorize("hasAnyAuthority(#customerID)")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse deleteCustomerById(@PathVariable("customerID") int customerID) {
        CustomerResponse customerResponse = customerService.findCustomerById(customerID);
        customerService.deleteCustomerByID(customerID);
        return customerResponse;
    }

    @PutMapping("/{customerID}")
    @PreAuthorize("hasAnyAuthority(#customerID)")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerResponse updateCustomer(@PathVariable("customerID") int customerID, @RequestBody UpdateCustomerForm form) {
        return customerService.updateCustomer(customerID, form);
    }
}
