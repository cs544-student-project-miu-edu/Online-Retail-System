package com.retail.ItemService.dto;

import com.retail.ItemService.domain.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private AddressResponse billingAddress;

    private List<AddressResponse> shippingAddresses = new ArrayList<>();

    private List<CreditCardResponse> creditCards = new ArrayList<>();

    public CustomerResponse() {
    }

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.billingAddress = new AddressResponse(customer.getBillingAddress());
        this.shippingAddresses = customer.getShippingAddresses().stream().map(AddressResponse::new).toList();
        this.creditCards = customer.getCreditCards().stream().map(CreditCardResponse::new).toList();
    }
}
