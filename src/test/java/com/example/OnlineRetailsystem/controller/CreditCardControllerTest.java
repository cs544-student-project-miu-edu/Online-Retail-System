package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.service.CreditCardService;
import com.example.OnlineRetailsystem.service.CreditCardServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreditCardServiceTest {
    @Test
    void testGetAllCreditCards() {
        // Create an instance of CreditCardService
        CreditCardService creditCardService = new CreditCardServiceImpl();

        // Call the getAllCreditCards() method
        List<CreditCard> allCreditCards = creditCardService.getAllCreditCards();

        // Assert that the returned list is not null
        assertNotNull(allCreditCards);

        // Add any additional assertions or test cases as needed
    }
}
