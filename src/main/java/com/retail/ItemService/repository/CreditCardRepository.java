package com.retail.ItemService.repository;

import com.retail.ItemService.domain.CreditCard;
import com.retail.ItemService.dto.CreditCardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    @Query("SELECT c.creditCards FROM Customer c WHERE c.id = :id")
    List<CreditCard> findById(@Param("id") int customerId);

    @Query("SELECT c.creditCards FROM Customer c WHERE c.id = :customerID")
    List<CreditCard> getCreditCardByCustomer(@Param("customerID") int customerID);
}
