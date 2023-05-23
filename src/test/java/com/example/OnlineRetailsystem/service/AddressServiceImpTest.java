package com.example.OnlineRetailsystem.service;
import com.example.OnlineRetailsystem.domain.Address;
import com.example.OnlineRetailsystem.service.AddressServiceImp;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressServiceImpTest {
    @Test
    void testGetAllAddress() {
        // Create an instance of AddressServiceImp
        AddressServiceImp addressService = new AddressServiceImp();

        // Call the getAllAddress() method
        List<Address> allAddresses = addressService.getAllAddress();

        // Assert that the returned list is not null
        assertNotNull(allAddresses);

        // Assert any other necessary conditions or expectations
        // For example, you can check the size of the list or specific properties of the addresses
        // For instance, if you expect an empty list, you can use assertEquals(0, allAddresses.size())
    }
}
