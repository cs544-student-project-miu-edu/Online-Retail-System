package com.retail.ItemService.repository;

import com.retail.ItemService.domain.Credential;
import com.retail.ItemService.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findCustomerByUsername(String username);
//    @Query("SELECT c.credentials FROM Customer c WHERE c.username = :username")
//    public Credential getCredential(String username);
}
