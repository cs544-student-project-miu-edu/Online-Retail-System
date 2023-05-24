package com.retail.ItemService.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class CreateCreditCardForm {
    @NotNull
    private String cardNumber;
    @NotNull
    private String expirationDate;
    @NotNull
    private String securityCode;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
