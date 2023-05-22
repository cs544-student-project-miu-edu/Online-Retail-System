package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itemLines")
public class ItemLine {

    @Id
    @GeneratedValue
    @Column(name = "ItemLineID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID")
    private Item item;

    private int quantity;
    private double discountValue;
}
