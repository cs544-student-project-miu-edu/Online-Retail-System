package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.*;
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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
//    List<Review> reviews = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
     List<Order> orderList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public void setDefaultShippingAddress(Address address) {
        if (shippingAddresses.contains(address)) {
            defaultShippingAddress = address;
        } else {
            throw new IllegalArgumentException("Customer doesnt have this address");
        }
    }
    //TODO - mesi

    // Sets the billing address and shipping addresses for the customer
    public void setAddresses(List<Address> addresses) {
        if (addresses != null && !addresses.isEmpty()) {
            Address billingAddress = addresses.get(0);
            billingAddress.setAddressType(AddressType.BILLINGADDRESS);
            this.billingAddress = billingAddress;
        }

        if (addresses != null && addresses.size() > 1) {
            this.shippingAddresses = addresses.subList(1, addresses.size()); //set default
        } else {
            this.shippingAddresses = new ArrayList<>();
        }
    }

    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(billingAddress);
        addresses.addAll(shippingAddresses);
        return addresses;
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public void setOrders(List<Order> orders) {
        this.orderList = orders;
    }

}
