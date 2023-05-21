package edu.miu.cs544.service;

import edu.miu.cs544.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(long id);
    boolean deleteById(long id);
    boolean addNewCustomer(CustomerDto customer);
    boolean updateCustomer(CustomerDto customerDto);

}
