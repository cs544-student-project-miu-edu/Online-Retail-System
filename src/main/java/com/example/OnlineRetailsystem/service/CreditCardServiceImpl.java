package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }
}
