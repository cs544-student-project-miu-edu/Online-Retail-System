package com.example.OnlineRetailsystem.form.customer;

public class CreateCustomerForm {
    private String firstName;
    private String lastName;
    private String email;
    private CreateAddressForm createAddressForm;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public CreateAddressForm getBillingAddressForm() {
        return createAddressForm;
    }
}
