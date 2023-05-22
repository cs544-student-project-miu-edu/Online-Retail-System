package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "compositeItems")
@AllArgsConstructor
@NoArgsConstructor
public class CompositeItem extends Item {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "composite_nestedItems")
    private List<Item> nestedItems = new ArrayList<>();
    public void removeNestedItem(Item item) {
        nestedItems.remove(item);
    }
    public void addNestedItem(Item item) {
        nestedItems.add(item);
    }

    @Override
    public double getPrice() {
        return calculatePrice();
    }

    private double calculatePrice() {
        double sum = 0;
        for (Item item : nestedItems) {
            sum += item.getPrice();
        }
        return sum;
    }
}
