package com.example.OnlineRetailsystem.dto;

import com.example.OnlineRetailsystem.domain.OrderState;
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

    private CustomerResponse customer;

    private AddressResponse shippingAddress;

    private OrderState status;

    private List<OrderLineResponse> lineItems = new ArrayList<>();


}
