package com.example.OnlineRetailsystem;

import com.example.OnlineRetailsystem.domain.Customer;
import com.example.OnlineRetailsystem.dto.CustomerResponse;

public class CustomerMapper {
    public CustomerResponse map(Customer customer, Class<CustomerResponse> responseType) {
        // Perform mapping logic here
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        // ...

        return response;
    }
}
