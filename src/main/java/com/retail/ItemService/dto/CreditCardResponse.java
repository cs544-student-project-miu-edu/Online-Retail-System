package com.retail.ItemService.dto;

import com.retail.ItemService.domain.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CreditCardResponse {

    private int id;
    private String number;
    private String expirationDate;
    private String securityCode;

    public CreditCardResponse() {
    }

    public CreditCardResponse(CreditCard card) {
        this.id = card.getId();
        this.number = card.getCardNumber();
        this.expirationDate = card.getExpirationDate();
        this.securityCode = card.getSecurityCode();
    }
}
