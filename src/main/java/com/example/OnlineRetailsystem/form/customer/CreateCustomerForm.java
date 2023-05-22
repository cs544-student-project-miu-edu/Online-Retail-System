package com.example.OnlineRetailsystem.form.customer;

public class CreateCustomerForm {
    private String firstName;
    private String lastName;
    private String email;
    private BillingAddressForm billingAddressForm;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddressForm getBillingAddressForm() {
        return billingAddressForm;
    }
}
