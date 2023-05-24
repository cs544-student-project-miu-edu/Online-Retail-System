package com.retail.ItemService.domain;

import com.retail.ItemService.ResponseError.NotFoundException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "billingAddressID")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private List<Address> shippingAddresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defaultShippingAddressID")
    private Address defaultShippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private List<CreditCard> creditCards;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Order> orderList = new ArrayList<>();


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
//    List<Review> reviews = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//     List<Order> orderList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(String firstName, String lastName, String email, Address billingAddress, Address defaultShippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddresses = new ArrayList<>();
        shippingAddresses.add(defaultShippingAddress);
        this.defaultShippingAddress = defaultShippingAddress;
        customerType = CustomerType.BUYER;
    }

    public void setDefaultShippingAddress(int addressID) {
        Optional<Address> newDefaultAddress = shippingAddresses.stream().filter(address -> address.getId() == addressID).findFirst();
        if (newDefaultAddress.isPresent()) {
            defaultShippingAddress = newDefaultAddress.get();
        } else {
            throw new NotFoundException("Customer doesnt have this address");
        }
    }
    //TODO - mesi

    // Sets the billing address and shipping addresses for the customer


//    public List<Order> getOrders() {
//        return orderList;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orderList = orders;
//    }

}