package com.retail.ItemService.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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


    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public Address() {
    }

    public Address(String street, String city, String state, String zipCode, AddressType addressType) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        Address address = (Address) obj;
        return this.getId() == address.getId() && this.getAddressType() == address.getAddressType() && this.getZipCode() == address.getZipCode()
                && this.getState() == address.getState() && this.getCity() == address.getCity() && this.getStreet() == address.getStreet();
    }
}
