package com.retail.ItemService.controller;

import com.retail.ItemService.dto.CreditCardResponse;
import com.retail.ItemService.form.CreateCreditCardForm;
import com.retail.ItemService.form.UpdateCreditCardForm;
import com.retail.ItemService.service.CreditCardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/customers/{customerID}/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardResponse createCreditCard(@PathVariable int customerID, @RequestBody @Valid CreateCreditCardForm form) {
        return creditCardService.createCreditCard(form, customerID);
    }

    @GetMapping("/customers/{customerID}/creditCard")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardResponse> getCustomerCreditCards(@PathVariable int customerID) {
        return creditCardService.getCustomersCreditCards(customerID);
    }

    @GetMapping("/customers/{customerID}/creditCard/{cardID}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCardResponse getCustomersCreditCard(@PathVariable int customerID, @PathVariable int cardID) {
        return mapper.map(creditCardService.getCustomersCreditCard(customerID, cardID), CreditCardResponse.class);
    }

    @PutMapping("/customers/{customerID}/creditCard/{cardID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCardResponse updateCustomerCreditCard(@PathVariable int customerID, @PathVariable int cardID, @RequestBody UpdateCreditCardForm form) {
        return creditCardService.updateCreditCard(customerID, cardID, form);
    }

    @DeleteMapping("/customers/{customerID}/creditCard/{cardID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCreditCard(@PathVariable int customerID, @PathVariable int cardID) {
        creditCardService.deleteCreditCard(customerID, cardID);
    }
}
