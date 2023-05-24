package com.retail.ItemService.controller;

import com.retail.ItemService.dto.AddressResponse;
import com.retail.ItemService.form.AddressForm;
import com.retail.ItemService.service.BillingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillingAddressController {
    @Autowired
    private BillingAddressService billingAddressService;

    @GetMapping("/customers/{customerID}/billingAddress")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getCustomerBillingAddress(@PathVariable int customerID) {
        return billingAddressService.getCustomerBillingAddress(customerID);
    }

    @PutMapping("/customers/{customerID}/billingAddress")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updateCustomerBillingAddress(@PathVariable int customerID, @RequestBody AddressForm form) {
        return billingAddressService.updateCustomerBillingAddress(customerID, form);
    }
}
