package com.retail.ItemService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LeafItems")
@Data
public class LeafItem extends Item {
    public LeafItem() {

    }

    public LeafItem(String name, String description, double price, String barcodeNumber, int quantity) {
        super(name, description, price, barcodeNumber, quantity);
    }
}
