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
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customerID")
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "billingAddressID")
    private Address billingAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> shippingAddresses = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defaultShippingAddressID")
    private Address defaultShippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private List<CreditCard> creditCards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Order> orderList = new ArrayList<>();

    public void setDefaultShippingAddress(Address address) {
        if (shippingAddresses.contains(address)) {
            defaultShippingAddress = address;
        } else {
            throw new IllegalArgumentException("Customer doesnt have this address");
        }
    }
}
