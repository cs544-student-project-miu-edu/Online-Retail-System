package com.example.OnlineRetailsystem.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.repository.CustomerRepository;
import com.example.OnlineRetailsystem.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_Success() {
        // Prepare mock data
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Bob");
        customer.setLastName("Tester");
        customer.setEmail("test@test.com");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Optional<Customer> result = customerRepository.findById(customerId);
        verify(customerRepository).findById(customerId);
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        // Prepare mock data
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        Optional<Customer> result = customerRepository.findById(customerId);
        verify(customerRepository).findById(customerId);
        assertFalse(result.isPresent());
    }

    // TODO - test the rest of the Repository methods
}
