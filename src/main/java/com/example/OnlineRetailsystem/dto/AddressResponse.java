package com.example.OnlineRetailsystem.dto;

import com.example.OnlineRetailsystem.domain.AddressType;
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
    private boolean isDefault;

    private CustomerResponse customerResponse;

    private AddressType addressType;


}
