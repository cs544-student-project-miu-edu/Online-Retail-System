package com.retail.ItemService.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CompositeItem extends Item {
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "composite_nestedItems")
    private List<Item> nestedItems = new ArrayList<>();

    public CompositeItem() {
    }

    public CompositeItem(String name, String description, double price, String barcodeNumber, int quantity) {
        super(name, description, price, barcodeNumber, quantity);
    }

    @Override
    public double getPrice() {
        return calculatePrice();
    }

    public void removeNestedItem(Item item) {
        nestedItems.remove(item);
    }

    public void addNestedItem(Item item) {
        nestedItems.add(item);
    }

    private double calculatePrice() {
        double sum = 0;
        for (Item item : nestedItems) {
            sum += item.getPrice();
        }
        return sum;
    }
}