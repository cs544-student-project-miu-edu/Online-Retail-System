package com.example.OnlineRetailsystem.dto;

import com.example.OnlineRetailsystem.domain.Item;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class CompositeItemResponse extends Item {
    private List<ItemResponse> subItems = new ArrayList<>();

    // Constructors, getters, and setters

    public void addSubItem(ItemResponse item) {
        subItems.add(item);
    }

    public void removeSubItem(ItemResponse item) {
        subItems.remove(item);
    }

    @Override
    public double getPrice() {
        return calculatePrice();
    }

    private double calculatePrice() {
        double sum = 0;
        for (ItemResponse item : subItems) {
            sum += item.getPrice();
        }
        return sum;
    }
}
