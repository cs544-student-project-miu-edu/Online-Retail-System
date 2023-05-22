package com.example.OnlineRetailsystem.repository;


import com.example.OnlineRetailsystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
