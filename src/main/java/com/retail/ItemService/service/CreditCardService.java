package com.retail.ItemService.service;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.HelperFunction;
import com.retail.ItemService.domain.CreditCard;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.dto.CreditCardResponse;
import com.retail.ItemService.form.CreateCreditCardForm;
import com.retail.ItemService.form.UpdateCreditCardForm;
import com.retail.ItemService.repository.CreditCardRepository;
import com.retail.ItemService.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    public CreditCardResponse createCreditCard(CreateCreditCardForm form, int customerID) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (!optionalCustomer.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        CreditCard newCreditCard = new CreditCard(form.getCardNumber(), form.getExpirationDate(), form.getSecurityCode());
        customer.getCreditCards().add(newCreditCard);
        creditCardRepository.saveAll(customer.getCreditCards());
        return mapper.map(newCreditCard, CreditCardResponse.class);
    }

    public List<CreditCardResponse> getCustomersCreditCards(int customerID) {
        List<CreditCard> cards = creditCardRepository.findById(customerID);
        return cards.stream().map(card -> mapper.map(card, CreditCardResponse.class)).toList();
    }

    public CreditCard getCustomersCreditCard(int customerID, int creditCardID) {
        Optional<CreditCard> creditCard = creditCardRepository.getCreditCardByCustomer(customerID).stream().filter(card -> card.getId() == creditCardID).findFirst();
        if (!creditCard.isPresent()) {
            throw new NotFoundException("Credit card not found for customer");
        }
        return creditCard.get();
    }

    public CreditCardResponse updateCreditCard(int customerID, int creditCardID, UpdateCreditCardForm form) {
        CreditCard existingCreditCard = getCustomersCreditCard(customerID, creditCardID);
        CreditCard updatingValues = new CreditCard(form.getCardNumber(), form.getExpirationDate(), form.getSecurityCode());
        int savedID = existingCreditCard.getId();
        HelperFunction.copyNonNullProperties(updatingValues, existingCreditCard);
        existingCreditCard.setId(savedID);
        CreditCard savedCreditCard = creditCardRepository.save(existingCreditCard);
        return mapper.map(savedCreditCard, CreditCardResponse.class);
    }

    public void deleteCreditCard(int customerID, int creditCardID) {
        CreditCard creditCard = getCustomersCreditCard(customerID, creditCardID);
        creditCardRepository.delete(creditCard);
    }
}
