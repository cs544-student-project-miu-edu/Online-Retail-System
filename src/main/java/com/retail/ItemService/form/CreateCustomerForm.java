package com.retail.ItemService.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

}
