package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.HelperFunction;
import com.retail.ItemService.domain.Address;
import com.retail.ItemService.domain.AddressType;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.dto.CustomerResponse;
import com.retail.ItemService.form.CreateCustomerForm;
import com.retail.ItemService.form.UpdateCustomerForm;
import com.retail.ItemService.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class CustomerService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CreateCustomerForm customer) {
        Address billingAddress = new Address(customer.getBillingAddressForm().getStreet(), customer.getBillingAddressForm().getCity(), customer.getBillingAddressForm().getState(), customer.getBillingAddressForm().getZipCode(), AddressType.BILLINGADDRESS);
        Address defaultShippingAddress = new Address(customer.getBillingAddressForm().getStreet(), customer.getBillingAddressForm().getCity(), customer.getBillingAddressForm().getState(), customer.getBillingAddressForm().getZipCode(), AddressType.SHIPPINGADDRESS);
        Customer newCustomer = new Customer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), billingAddress, defaultShippingAddress);
        Customer createdCustomer = customerRepository.save(newCustomer);
        return mapper.map(createdCustomer, CustomerResponse.class);
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(entity -> mapper.map(entity, CustomerResponse.class));
    }

    public CustomerResponse findCustomerById(int id) {
        Customer customer = findCustomerId(id);
        return mapper.map(customer, CustomerResponse.class);
    }

    public Customer findCustomerId(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        return customer.get();
    }

    public void deleteCustomerByID(int id) {
        customerRepository.deleteById(id);
        return;
    }

    public CustomerResponse updateCustomer(int id, UpdateCustomerForm form) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Customer updatedCustomer = new Customer(form.getFirstName(), form.getLastName(), form.getEmail());
        Customer existingCustomer = customer.get();
        int savedID = existingCustomer.getId();
        HelperFunction.copyNonNullProperties(updatedCustomer, existingCustomer);
        existingCustomer.setId(savedID);
        if (form.getPrimaryShippingAddress() != 0) {
            existingCustomer.setDefaultShippingAddress(form.getPrimaryShippingAddress());
        }
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return mapper.map(savedCustomer, CustomerResponse.class);
    }

}
