package com.retail.ItemService.repository;

import com.retail.ItemService.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT c.billingAddress FROM Customer c WHERE c.id = :customerID")
    Optional<Address> getCustomerBillingAddress(@Param("customerID") int customerID);

    @Query("SELECT c.shippingAddresses FROM Customer c WHERE c.id = :customerID")
    List<Address> getCustomerShippingAddress(@Param("customerID") int customerID);
}

