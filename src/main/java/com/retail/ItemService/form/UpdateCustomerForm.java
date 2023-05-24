package com.retail.ItemService.form;

import jakarta.validation.constraints.NotBlank;

public class UpdateCustomerForm {

    private String firstName;
    private String lastName;
    private String email;
    private int primaryShippingAddress;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPrimaryShippingAddress() {
        return primaryShippingAddress;
    }
}
