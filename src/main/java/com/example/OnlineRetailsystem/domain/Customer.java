package com.example.OnlineRetailsystem.domain;

import com.example.OnlineRetailsystem.customExceptions.NotFoundException;
import jakarta.persistence.*;
import lombok.*;
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

    //TODO - mesi - fixed
    public void setDefaultShippingAddress(Integer addressID) {
        if (addressID == null) {
            defaultShippingAddress = null;
            return;
        }
        Optional<Address> newDefaultAddress = shippingAddresses.stream().filter(address -> address.getId() == addressID).findFirst();
        if (newDefaultAddress.isPresent()) {
            defaultShippingAddress = newDefaultAddress.get();
        } else {
            throw new NotFoundException("Customer doesnt have this address");
        }
    }

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
