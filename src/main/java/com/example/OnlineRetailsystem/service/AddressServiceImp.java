package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
}
