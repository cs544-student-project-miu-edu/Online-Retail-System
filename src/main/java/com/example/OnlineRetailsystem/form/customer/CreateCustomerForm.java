package com.example.OnlineRetailsystem.form.customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
public class CreateCustomerForm {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @JsonProperty("address")
    @NotNull
    private AddressForm address;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public AddressForm getBillingAddressForm() {
        return address;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return  this.password;
    }
}