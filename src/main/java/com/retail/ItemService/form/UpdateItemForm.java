package com.retail.ItemService.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UpdateItemForm {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Min(1)
    private double price;
    @NotBlank
    private String barcodeNumber;
    @Min(1)
    private int quantity;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}
