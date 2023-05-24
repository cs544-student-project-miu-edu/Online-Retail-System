package com.retail.ItemService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    public ItemLine() {
    }

    public ItemLine(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}