package com.retail.ItemService.dto;

import com.retail.ItemService.domain.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

}
