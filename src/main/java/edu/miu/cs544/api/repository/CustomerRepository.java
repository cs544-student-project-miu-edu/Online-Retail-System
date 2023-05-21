package edu.miu.cs544.api.repository;

import edu.miu.cs544.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer getCustomersByUserId(Long userId);
}
