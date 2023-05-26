package com.retail.ItemService.controller;

import com.retail.ItemService.dto.AddressResponse;
import com.retail.ItemService.form.AddressForm;
import com.retail.ItemService.service.ShippingAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping("/customers/{customerID}/shippingAddress")
    @PreAuthorize("hasRole(#customerID)")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> getCustomerShippingAddress(@PathVariable("customerID") int customerID) {
        return shippingAddressService.getShippingAddress(customerID);
    }

    @PostMapping("/customers/{customerID}/shippingAddress")
    @PreAuthorize("hasRole(#customerID)")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse addShippingAddressToCustomer(@PathVariable("customerID") int customerID, @Valid @RequestBody AddressForm form) {
        return shippingAddressService.addShippingAddress(customerID, form);
    }

    @DeleteMapping("/customers/{customerID}/shippingAddress/{addressID}")
    @PreAuthorize("hasRole(#customerID)")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShippingAddress(@PathVariable("customerID") int customerID, @PathVariable int addressID) {
        shippingAddressService.deleteShippingAddress(customerID, addressID);
    }

    @PutMapping("/customers/{customerID}/shippingAddress/{addressID}")
    @PreAuthorize("hasRole(#customerID)")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updatedShippingAddress(@PathVariable("customerID") int customerID, @PathVariable int addressID, @Valid @RequestBody AddressForm form) {
        return shippingAddressService.updateAddress(customerID, addressID, form);
    }
}
