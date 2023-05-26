package com.retail.ItemService.dto;

import com.retail.ItemService.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private byte[] image;
    private String barcodeNumber;
    private int quantityInStock;

    public ItemResponse() {
    }

    public ItemResponse(Item item) {
        this.id = item.getItemID();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.barcodeNumber = item.getBarcodeNumber();
        this.quantityInStock = item.getQuantity();
        this.reviews = item.getReviews().stream().map(review -> new ReviewResponse(review)).toList();
    }

    private List<ReviewResponse> reviews = new ArrayList<>();
}
