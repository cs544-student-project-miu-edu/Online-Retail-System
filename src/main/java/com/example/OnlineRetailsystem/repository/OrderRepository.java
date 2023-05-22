package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
