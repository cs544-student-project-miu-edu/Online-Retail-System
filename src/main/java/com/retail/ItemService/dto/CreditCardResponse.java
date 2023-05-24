package com.retail.ItemService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {

    private int id;
    private String number;
    private String expirationDate;
    private String securityCode;
}
