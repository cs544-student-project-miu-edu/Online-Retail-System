package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository <CreditCard, Integer> {
    List<CreditCard> findByCustomerId(int customerId);
}
