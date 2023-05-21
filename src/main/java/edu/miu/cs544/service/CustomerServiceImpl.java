package edu.miu.cs544.service;

import edu.miu.cs544.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

     @Override
    public List<CustomerDto> findAll() {
        return null;
    }

    @Override
    public CustomerDto findById(long id) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public boolean addNewCustomer(CustomerDto customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        return false;
    }
}
