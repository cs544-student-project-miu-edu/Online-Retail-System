package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Collection<Item> findByNameContainingIgnoreCase(String name);
}
