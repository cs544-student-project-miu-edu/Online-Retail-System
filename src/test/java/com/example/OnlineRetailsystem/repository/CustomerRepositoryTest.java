package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindById_ExistingId_ReturnsCustomer() {
        // Given
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");

        customerRepository.save(customer);

        // When
        Customer foundCustomer = customerRepository.findById(1).orElse(null);

        // Then
        assertNotNull(foundCustomer);
        assertEquals(customer.getId(), foundCustomer.getId());
        assertEquals(customer.getFirstName(), foundCustomer.getFirstName());
        // Add assertions for other properties of the customer
    }

    @Test
    public void testFindById_NonExistingId_ReturnsNull() {
        // When
        Customer foundCustomer = customerRepository.findById(1).orElse(null);

        // Then
        assertNull(foundCustomer);
    }

    // Add more test methods for other repository operations if needed
}
