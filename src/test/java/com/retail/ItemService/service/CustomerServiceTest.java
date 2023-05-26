package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.*;
import com.retail.ItemService.dto.*;
import com.retail.ItemService.form.*;
import com.retail.ItemService.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.*;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private ModelMapper mockMapper;

    @Mock
    public CustomerRepository mockCustomerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        // Prepare
        CreateCustomerForm form = new CreateCustomerForm();
        form.setFirstName("John");
        form.setLastName("Doe");
        form.setEmail("johndoe@example.com");
        form.setUsername("johndoe");
        form.setPassword("password");

        AddressForm billingAddressForm = mock(AddressForm.class); // Create a mock object of AddressForm
        // Set the necessary properties of the mock AddressForm
        when(billingAddressForm.getStreet()).thenReturn("Street 1");
        when(billingAddressForm.getCity()).thenReturn("City");
        when(billingAddressForm.getState()).thenReturn("State");
        when(billingAddressForm.getZipCode()).thenReturn("12345");
        form.setAddress(billingAddressForm); // Set the mock AddressForm in CreateCustomerForm

        //form.setBillingAddressForm(billingAddressForm);

        Address billingAddress = new Address("Street 1", "City", "State", "12345", AddressType.BILLINGADDRESS);
        Address defaultShippingAddress = new Address("Street 2", "City", "State", "12345", AddressType.SHIPPINGADDRESS);

        Customer newCustomer = new Customer("John", "Doe", "johndoe@example.com", billingAddress, defaultShippingAddress);
        newCustomer.setCredintials(new Credintials("johndoe", "password"));

        CustomerResponse expectedResponse = new CustomerResponse();
        when(mockMapper.map(any(Customer.class), eq(CustomerResponse.class))).thenReturn(expectedResponse);
        when(mockCustomerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        // Execute
        CustomerResponse actualResponse = customerService.createCustomer(form);

        // Verify
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(mockCustomerRepository, times(1)).save(any(Customer.class));
        verify(mockMapper, times(1)).map(any(Customer.class), eq(CustomerResponse.class));
    }


//TODO - come back here
    /*
    @Test
    public void testGetAllCustomers() {
        // Prepare
        Page<Customer> customersPage = mock(Page.class);
        when(mockCustomerRepository.findAll(any(Pageable.class))).thenReturn(customersPage);

        Page<CustomerResponse> expectedResponsePage = mock(Page.class);
        when(customersPage.map(any(Function.class))).thenReturn(expectedResponsePage);

        Page<Customer> customerPage = mock(Page.class);
        when(mockCustomerRepository.findAll(any(Pageable.class))).thenReturn(customerPage);

        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("John", "Doe", "johndoe@example.com");
        Customer customer2 = new Customer("Jane", "Smith", "janesmith@example.com");
        customers.add(customer1);
        customers.add(customer2);
        when(customerPage.getContent()).thenReturn(customers);

        CustomerResponse customerResponse1 = new CustomerResponse();
        CustomerResponse customerResponse2 = new CustomerResponse();
        when(mockMapper.map(customer1, CustomerResponse.class)).thenReturn(customerResponse1);
        when(mockMapper.map(customer2, CustomerResponse.class)).thenReturn(customerResponse2);

        Pageable pageable = mock(Pageable.class);

        // Execute
        Page<CustomerResponse> actualResponsePage = customerService.getAllCustomers(pageable);

        // Verify
        assertNotNull(actualResponsePage);
        assertEquals(expectedResponsePage, actualResponsePage);
        verify(mockCustomerRepository, times(1)).findAll(any(Pageable.class));
        verify(mockMapper, times(1)).map(customer1, CustomerResponse.class);
        verify(mockMapper, times(1)).map(customer2, CustomerResponse.class);
    }

*/
    @Test
    public void testFindCustomerById_CustomerExists() {
        // Prepare
        int customerId = 1;
        Customer customer = new Customer("John", "Doe", "johndoe@example.com");
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerResponse expectedResponse = new CustomerResponse();
        when(mockMapper.map(any(Customer.class), eq(CustomerResponse.class))).thenReturn(expectedResponse);

        // Execute
        CustomerResponse actualResponse = customerService.findCustomerById(customerId);

        // Verify
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(mockCustomerRepository, times(1)).findById(customerId);
        verify(mockMapper, times(1)).map(any(Customer.class), eq(CustomerResponse.class));
    }

    @Test
    public void testFindCustomerById_CustomerNotFound() {
        // Prepare
        int customerId = 1;
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Execute and Verify
        assertThrows(NotFoundException.class, () -> customerService.findCustomerById(customerId));
        verify(mockCustomerRepository, times(1)).findById(customerId);
        verify(mockMapper, never()).map(any(Customer.class), eq(CustomerResponse.class));
    }

    @Test
    public void testDeleteCustomerByID() {
        // Prepare
        int customerId = 1;

        // Execute
        customerService.deleteCustomerByID(customerId);

        // Verify
        verify(mockCustomerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testUpdateCustomer_CustomerExists() {
        // Prepare
        int customerId = 1;
        UpdateCustomerForm form = new UpdateCustomerForm();
        form.setFirstName("UpdatedFirstName");
        form.setLastName("UpdatedLastName");
        form.setEmail("updatedemail@example.com");
        form.setPrimaryShippingAddress("2");

        Customer existingCustomer = new Customer("John", "Doe", "johndoe@example.com");
        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        Customer updatedCustomer = new Customer("UpdatedFirstName", "UpdatedLastName", "updatedemail@example.com");
        updatedCustomer.setId(existingCustomer.getId());

        Customer savedCustomer = new Customer("SavedFirstName", "SavedLastName", "savedemail@example.com");
        savedCustomer.setId(existingCustomer.getId());

        when(mockCustomerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        when(mockMapper.map(any(Customer.class), eq(CustomerResponse.class))).thenReturn(new CustomerResponse());

        // Execute
        CustomerResponse actualResponse = customerService.updateCustomer(customerId, form);

        // Verify
        assertNotNull(actualResponse);
        verify(mockCustomerRepository, times(1)).findById(customerId);
        verify(mockCustomerRepository, times(1)).save(any(Customer.class));
        verify(mockMapper, times(1)).map(any(Customer.class), eq(CustomerResponse.class));
    }

    @Test
    public void testUpdateCustomer_CustomerNotFound() {
        // Prepare
        int customerId = 1;
        UpdateCustomerForm form = new UpdateCustomerForm();
        form.setFirstName("UpdatedFirstName");
        form.setLastName("UpdatedLastName");
        form.setEmail("updatedemail@example.com");
        form.setPrimaryShippingAddress("2");

        when(mockCustomerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Execute and Verify
        assertThrows(NotFoundException.class, () -> customerService.updateCustomer(customerId, form));
        verify(mockCustomerRepository, times(1)).findById(customerId);
        verify(mockCustomerRepository, never()).save(any(Customer.class));
        verify(mockMapper, never()).map(any(Customer.class), eq(CustomerResponse.class));
    }
}

