package com.retail.ItemService.controller;

import com.retail.ItemService.dto.AddressResponse;
import com.retail.ItemService.form.AddressForm;
import com.retail.ItemService.service.ShippingAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping("/customers/{customerID}/shippingAddress")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> getCustomerShippingAddress(@PathVariable int customerID) {
        return shippingAddressService.getShippingAddress(customerID);
    }

    @PostMapping("/customers/{customerID}/shippingAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse addShippingAddressToCustomer(@PathVariable int customerID, @Valid @RequestBody AddressForm form) {
        return shippingAddressService.addShippingAddress(customerID, form);
    }

    @DeleteMapping("/customers/{customerID}/shippingAddress/{addressID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShippingAddress(@PathVariable int customerID, @PathVariable int addressID) {
        shippingAddressService.deleteShippingAddress(customerID, addressID);
    }

    @PutMapping("/customers/{customerID}/shippingAddress/{addressID}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updatedShippingAddress(@PathVariable int customerID, @PathVariable int addressID, @Valid @RequestBody AddressForm form) {
        return shippingAddressService.updateAddress(customerID, addressID, form);
    }
}
