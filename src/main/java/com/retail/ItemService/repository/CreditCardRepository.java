package com.retail.ItemService.repository;

import com.retail.ItemService.domain.CreditCard;
import com.retail.ItemService.dto.CreditCardResponse;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("SELECT c.creditCards FROM Customer c WHERE c.id = :customerID")
    List<CreditCard> getCreditCardByCustomer(@Param("customerID") int customerID);
}
