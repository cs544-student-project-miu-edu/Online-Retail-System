package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue
    private int itemID;
    private String name;
    private String description;
    private double price;
    private byte[] image;
    private String barcodeNumber;
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID")
    private List<Review> reviews = new ArrayList<>();
}
