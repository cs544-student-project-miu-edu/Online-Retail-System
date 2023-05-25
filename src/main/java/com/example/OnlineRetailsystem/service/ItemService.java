package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.domain.Item;


import java.util.Collection;
import java.util.List;

public interface ItemService {
    public Item createItem(Item item);
    public void deleteItem(int itemID);
    public Item getItemById(int itemID);
    public Item updateItem(int itemID, Item item);
    public List<Item> getAllItems();

    List<Review> getItemReviews(int itemId);
    public Review addReviewToItem(int itemId, Review review);
    public Review updateReviewToItem(int itemId, Review review);
    public void deleteReviewToItem(int itemId, Long reviewID);

    Collection<Item> search(String name);
}
