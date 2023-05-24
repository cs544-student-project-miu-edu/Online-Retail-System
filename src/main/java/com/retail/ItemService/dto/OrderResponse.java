package com.retail.ItemService.dto;


import com.retail.ItemService.domain.ItemLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private int id;

//    private CustomerResponse customer;

    private AddressResponse shippingAddress;


    private List<ItemLine> lineItems = new ArrayList<>();


}
