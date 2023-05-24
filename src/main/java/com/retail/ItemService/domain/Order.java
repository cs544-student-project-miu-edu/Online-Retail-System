package com.retail.ItemService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY) // Added fetch type
    @JoinColumn(name = "customerID")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "shippingAddressID")
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "orderID")
    private List<ItemLine> lineItems = new ArrayList<>();

}