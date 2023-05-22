package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.domain.Item;


import java.util.Collection;
import java.util.List;

public interface ItemService {
    public Item createItem(Item item);
    public void deleteItem(int itemID);

    public void updateItem(int itemId, Item item);
    public List<Item> getAllItems();
    List<Item> getItemReviews(int itemId);
    Collection<Item> search(String name);
}
