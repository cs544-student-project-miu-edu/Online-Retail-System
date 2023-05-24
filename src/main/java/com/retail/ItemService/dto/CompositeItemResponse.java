package com.retail.ItemService.dto;

import com.retail.ItemService.domain.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompositeItemResponse extends Item {
    private List<ItemResponse> nestedItems;

    // Constructors, getters, and setters

    public void addSubItem(ItemResponse item) {
        nestedItems.add(item);
    }

    public void removeSubItem(ItemResponse item) {
        nestedItems.remove(item);
    }

    @Override
    public double getPrice() {
        return calculatePrice();
    }

    private double calculatePrice() {
        double sum = 0;
        for (ItemResponse item : nestedItems) {
            sum += item.getPrice();
        }
        return sum;
    }
}
