package com.retail.ItemService.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Valid
public class CreateItemForm {
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
    @NotNull
    @JsonProperty("items")
    @Valid
    private List<CreateItemForm> items;

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

    public List<CreateItemForm> getItems() {
        return items;
    }
}
