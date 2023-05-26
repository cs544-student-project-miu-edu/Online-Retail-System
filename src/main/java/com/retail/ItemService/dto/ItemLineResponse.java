package com.retail.ItemService.dto;

import com.retail.ItemService.domain.ItemLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ItemLineResponse {
    private int id;
    private ItemResponse item;
    private int quantity;
    private double discount;

    public ItemLineResponse() {
    }

    public ItemLineResponse(ItemLine line) {
        this.id = line.getId();
        this.item = new ItemResponse(line.getItem());
        this.quantity = line.getQuantity();
        this.discount = line.getDiscountValue();
    }
}
