package com.example.OnlineRetailsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.OnlineRetailsystem.CustomerMapper;
import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.dto.CustomerResponse;
import com.example.OnlineRetailsystem.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindCustomerById() {
        int customerId = 1;

        // Create sample customer data
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John Doe");
        // ...

        // Create a sample customer response
        CustomerResponse expectedResponse = new CustomerResponse();
        expectedResponse.setId(customerId);
        expectedResponse.setFirstName("John Doe");
        // ...

        // Mocking the behavior of customerRepository.findById
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Mocking the behavior of mapper.map
        when(mapper.map(customer, CustomerResponse.class)).thenReturn(expectedResponse);

        // Call the service method
        CustomerResponse actualResponse = customerService.findCustomerById(customerId);

        // Verify the interactions and assertions
        verify(customerRepository).findById(customerId);
        verify(mapper).map(customer, CustomerResponse.class);
        Assert.assertEquals(expectedResponse, actualResponse);
    }
}
