package com.example.OnlineRetailsystem.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import com.example.OnlineRetailsystem.dto.CustomerResponse;
import com.example.OnlineRetailsystem.service.CustomerService;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerById_Success() {
        int customerId = 1;
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customerId);
        customerResponse.setFirstName("Bob");
        customerResponse.setLastName("Tester");
        customerResponse.setEmail("test@test.com");
        when(customerService.findCustomerById(customerId)).thenReturn(customerResponse);
        CustomerResponse response = customerController.getCustomerById(customerId);
        verify(customerService).findCustomerById(customerId);
        assertEquals(customerResponse, response);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        int customerId = 1;
        when(customerService.findCustomerById(customerId)).thenReturn(null);
        CustomerResponse response = customerController.getCustomerById(customerId);
        verify(customerService).findCustomerById(customerId);
        assertNull(response);
    }
    // TODO - test the rest of the controller methods
}