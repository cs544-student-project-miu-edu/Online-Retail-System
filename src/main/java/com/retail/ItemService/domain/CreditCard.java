package com.retail.ItemService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "creditCards")
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue
    private int id;
    private String cardNumber;
    private String expirationDate;
    private String securityCode;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String expirationDate, String securityCode) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
