package com.retail.ItemService.form;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class UpdateCreditCardForm {
    private String cardNumber;
    private String expirationDate;

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
