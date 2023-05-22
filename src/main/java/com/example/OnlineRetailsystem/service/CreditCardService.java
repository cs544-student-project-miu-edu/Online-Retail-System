package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreditCard createCreditCard(CreditCard creditCard);
    public void deleteCreditCard(int creditCardID);

    public void updateCreditCard(int creditCardId, CreditCard creditCard);
    public CreditCard getCreditCard(int creditCardId);
    public List<CreditCard> getAllCreditCards();
}
