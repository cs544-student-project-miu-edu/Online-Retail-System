package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {
    @Id
    @GeneratedValue
    private int itemID;
    private String name;
    private String description;
    private double price;
    @Transient
    private byte[] image;
    private String barcodeNumber;
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID")
    private List<Review> reviews = new ArrayList<>();
    public Item(String name, String description, double price, byte[] image, String barcodeNumber, int quantity){
        this.name=name;
        this.description=description;
        this.price=price;
        this.image=image;
        this.barcodeNumber=barcodeNumber;
        this.quantity=quantity;
    }
}
