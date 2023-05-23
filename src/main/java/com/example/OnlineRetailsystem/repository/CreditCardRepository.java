package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CreditCardRepository extends JpaRepository <CreditCard, Integer> {

    @Query("SELECT c FROM CreditCard c WHERE c.id = :customerId")
    List<CreditCard> findByCustomerId(@Param("customerId") int customerId);

}
