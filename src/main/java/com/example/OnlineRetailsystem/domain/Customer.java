package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customerID")
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne
    private Address billingAddress;
    @OneToMany
    @JoinTable(name = "customerAddress")
    private List<Address> shippingAddresses = new ArrayList<>();
    @OneToOne
    private Address defaultShippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private List<CreditCard> creditCards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Order> orderList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, Address billingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingAddress = billingAddress;
        this.defaultShippingAddress = billingAddress;
        this.defaultShippingAddress.setAddressType(AddressType.SHIPPINGADDRESS);
        this.addShippingAddress(this.defaultShippingAddress);
        this.customerType = CustomerType.BUYER;
    }

    public void addShippingAddress(Address address) {
        shippingAddresses.add(address);
    }

    public void setDefaultShippingAddress(Address address) {
        if (shippingAddresses.contains(address)) {
            defaultShippingAddress = address;
        } else {
            throw new IllegalArgumentException("Customer doesnt have this address");
        }
    }
}
