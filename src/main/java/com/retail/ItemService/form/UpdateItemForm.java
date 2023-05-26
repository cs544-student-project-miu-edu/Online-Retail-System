package com.retail.ItemService.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public UpdateItemForm(){
    }

    public UpdateItemForm(String name, String description, double price, String barcodeNumber, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.barcodeNumber = barcodeNumber;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
