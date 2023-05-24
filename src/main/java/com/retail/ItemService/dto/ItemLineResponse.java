package com.retail.ItemService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ItemLineResponse {

    private int id;

    private ItemResponse item;

    private int quantity;
    private double discount;


}
