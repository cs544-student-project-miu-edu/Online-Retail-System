package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.CreditCard;
import com.example.OnlineRetailsystem.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/creditCrd")
public class CreditCardController {

//    @Autowired
//    CreditCardService creditCardService;
//    @GetMapping("/")
//
//    public List<CreditCard> getAllCreditCard(){
//        return creditCardService.getAllCreditCards();
//    }
}
