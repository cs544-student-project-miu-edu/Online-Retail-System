package com.retail.ItemService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ItemResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private byte[] image;
    private String barcodeNumber;
    private int quantityInStock;

    private List<ReviewResponse> reviews = new ArrayList<>();
}
