package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor

@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "addressID")
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public Address(String street, String city, String state, String zipCode, AddressType addressType) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.addressType = addressType;
    }

    public Address() {
    }
}
