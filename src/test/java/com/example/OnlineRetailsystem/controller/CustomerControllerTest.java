package com.example.OnlineRetailsystem.controller;
import com.example.OnlineRetailsystem.dto.CustomerResponse;
import com.example.OnlineRetailsystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Mocking the service response
        CustomerResponse customer1 = new CustomerResponse();
        customer1.setId(1);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");

        CustomerResponse customer2 = new CustomerResponse();
        customer2.setId(2);
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");

        List<CustomerResponse> customerResponses = Arrays.asList(customer1, customer2);
        Page<CustomerResponse> customerResponsePage = new PageImpl<>(customerResponses);
        when(customerService.getAllCustomers(ArgumentMatchers.any(Pageable.class))).thenReturn(customerResponsePage);

        Page<CustomerResponse> responseEntity = customerController.getAllCustomers(null);

        // Verifying the service method was called
        verify(customerService, times(1)).getAllCustomers(ArgumentMatchers.any(Pageable.class));

        // Verifying the response data
        List<CustomerResponse> customerList = responseEntity.getContent();
        assertEquals(customerResponses.size(), customerList.size());
        assertEquals(customer1.getId(), customerList.get(0).getId());
        assertEquals(customer1.getFirstName(), customerList.get(0).getFirstName());
        assertEquals(customer1.getLastName(), customerList.get(0).getLastName());
        assertEquals(customer2.getId(), customerList.get(1).getId());
        assertEquals(customer2.getFirstName(), customerList.get(1).getFirstName());
        assertEquals(customer2.getLastName(), customerList.get(1).getLastName());
    }

}
