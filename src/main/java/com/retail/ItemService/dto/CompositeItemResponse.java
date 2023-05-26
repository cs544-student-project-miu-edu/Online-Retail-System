package com.retail.ItemService.dto;

import com.retail.ItemService.domain.CompositeItem;
import com.retail.ItemService.domain.Item;
import com.retail.ItemService.domain.LeafItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompositeItemResponse extends ItemResponse {
    private List<ItemResponse> nestedItems;



    // Constructors, getters, and setters

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
