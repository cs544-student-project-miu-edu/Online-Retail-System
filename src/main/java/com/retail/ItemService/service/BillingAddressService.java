package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.HelperFunction;
import com.retail.ItemService.domain.Address;
import com.retail.ItemService.domain.AddressType;
import com.retail.ItemService.dto.AddressResponse;
import com.retail.ItemService.form.AddressForm;
import com.retail.ItemService.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class BillingAddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper mapper;

    public AddressResponse getCustomerBillingAddress(int customerID) {
        Optional<Address> optionalAddress = addressRepository.getCustomerBillingAddress(customerID);
        if (!optionalAddress.isPresent()) {
            throw new NotFoundException("Billing address not found");
        }
        return mapper.map(optionalAddress.get(), AddressResponse.class);
    }

    public AddressResponse updateCustomerBillingAddress(int customerID, AddressForm form) {
        Optional<Address> optionalAddress = addressRepository.getCustomerBillingAddress(customerID);
        if (!optionalAddress.isPresent()) {
            throw new NotFoundException("Billing address not found");
        }
        Address existingAddress = optionalAddress.get();
        Address updatingValues = new Address(form.getStreet(), form.getCity(), form.getState(), form.getZipCode(), null);
        int savedID = existingAddress.getId();
        HelperFunction.copyNonNullProperties(updatingValues, existingAddress);
        existingAddress.setId(savedID);
        Address savedAddress = addressRepository.save(existingAddress);
        return mapper.map(savedAddress, AddressResponse.class);
    }
}
