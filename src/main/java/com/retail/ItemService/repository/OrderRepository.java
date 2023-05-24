package com.retail.ItemService.repository;

import com.retail.ItemService.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerID AND o.state = 'NEW' ")
    Optional<Order> findOrderByCustomer(@Param("customerID") int customerID);
    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerID")
    Optional<Order> findAllOrderByCustomer(@Param("customerID") int customerID);
}
