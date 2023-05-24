package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
        List<Address> findAddressByCustomerId(int customerId);
}
