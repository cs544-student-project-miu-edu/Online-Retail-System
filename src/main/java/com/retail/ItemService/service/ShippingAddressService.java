package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.HelperFunction;
import com.retail.ItemService.domain.Address;
import com.retail.ItemService.domain.AddressType;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.dto.AddressResponse;
import com.retail.ItemService.form.AddressForm;
import com.retail.ItemService.repository.AddressRepository;
import com.retail.ItemService.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ShippingAddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    public AddressResponse addShippingAddress(int customerID, AddressForm form) {
        Optional<Customer> customerOptional = customerRepository.findById(customerID);
        if (!customerOptional.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Address shippingAddress = new Address(form.getStreet(), form.getCity(), form.getState(), form.getZipCode(), AddressType.SHIPPINGADDRESS);
        Customer customer = customerOptional.get();
        customer.getShippingAddresses().add(shippingAddress);
        addressRepository.saveAll(customer.getShippingAddresses());
        return mapper.map(shippingAddress, AddressResponse.class);
    }

    public List<AddressResponse> getShippingAddress(int customerID) {
        return addressRepository.getCustomerShippingAddress(customerID).stream().map(address -> mapper.map(address, AddressResponse.class)).toList();
    }

    public void deleteShippingAddress(int customerID, int shippingAddressID) {
        Optional<Customer> customerOptional = customerRepository.findById(customerID);
        if (!customerOptional.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Optional<Address> shippingAddress = customerOptional.get().getShippingAddresses().stream().filter(address -> address.getId() == shippingAddressID).findAny();
        if (!shippingAddress.isPresent()) {
            throw new NotFoundException("Shipping address not found");
        }
        addressRepository.delete(shippingAddress.get());
    }

    public AddressResponse updateAddress(int customerID, int shippingAddressID, AddressForm form) {
        Optional<Customer> customerOptional = customerRepository.findById(customerID);
        if (!customerOptional.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Optional<Address> shippingAddress = customerOptional.get().getShippingAddresses().stream().filter(address -> address.getId() == shippingAddressID).findAny();
        if (!shippingAddress.isPresent()) {
            throw new NotFoundException("Shipping address not found");
        }
        Address updatedAddress = new Address(form.getStreet(), form.getCity(), form.getState(), form.getZipCode(), null);
        Address existingAddress = shippingAddress.get();
        int savedID = existingAddress.getId();
        HelperFunction.copyNonNullProperties(updatedAddress, existingAddress);
        existingAddress.setId(savedID);
        Address savedAddress = addressRepository.save(existingAddress);
        return mapper.map(savedAddress, AddressResponse.class);
    }
}
