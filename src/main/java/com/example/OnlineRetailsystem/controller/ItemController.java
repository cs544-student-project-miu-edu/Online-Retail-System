package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Item;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface ItemController {

    ResponseEntity<Item> createItem(Item item);
    ResponseEntity<Item> updateItem(int itemID, Item item);
    ResponseEntity<Void> deleteItem(int itemID);
    ResponseEntity<Collection<Item>> getAllItems();
    ResponseEntity<Item> getItemByID(int itemID);
    ResponseEntity<Collection<Item>> search(String name);
}
