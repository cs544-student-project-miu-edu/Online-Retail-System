package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private AddressService addressService;
    @GetMapping("/")
    public List<Address> getAllAddress(){
       return addressService.getAllAddress();
    }

}
