package com.retail.ItemService.dto;


import com.retail.ItemService.domain.ItemLine;
import com.retail.ItemService.domain.Order;
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


    private List<ItemLineResponse> lineItems = new ArrayList<>();

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.shippingAddress = new AddressResponse(order.getShippingAddress());
        this.lineItems = order.getLineItems().stream().map(ItemLineResponse::new).toList();
    }
}
