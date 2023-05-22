package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Address() {
    }

    public Address(String street, String city, String state, String zipCode, AddressType addressType) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.addressType = addressType;
    }

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
