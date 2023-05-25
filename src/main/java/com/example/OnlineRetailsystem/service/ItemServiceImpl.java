package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.domain.Review;
import com.example.OnlineRetailsystem.dto.CustomMessageType;
import com.example.OnlineRetailsystem.repository.ItemRepository;
import com.example.OnlineRetailsystem.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(int itemID) {
        itemRepository.deleteById(itemID);
    }

    @Override
    public Item getItemById(int itemID) {
        return itemRepository.findById(itemID)
                .orElseThrow(() -> new CustomMessageType("Item not found with ID: " + itemID));
    }

    @Override
    public Item updateItem(int itemId, Item item) {
        Item existingItem = getItemById(itemId);
        existingItem.setName(item.getName());
        existingItem.setDescription(item.getDescription());
        existingItem.setPrice(item.getPrice());
        existingItem.setImage(item.getImage());
        existingItem.setBarcodeNumber(item.getBarcodeNumber());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setReviews(item.getReviews());
        return itemRepository.save(existingItem);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Review> getItemReviews(int itemId) {
       Item item = getItemById(itemId);
       return item.getReviews();
    }

    @Override
    public Review addReviewToItem(int itemId, Review review) {
        Item existingItem = getItemById(itemId);
        List<Review> reviews = existingItem.getReviews();
        reviews.add(review);
        existingItem.setReviews(reviews);
        itemRepository.save(existingItem);
        return reviewRepository.save(review);

    }

    @Override
    public Review updateReviewToItem(int itemId, Review review) {
        Item existingItem = getItemById(itemId);
        List<Review> reviews = existingItem.getReviews();

        for (Review r : reviews){
            if(r.getId() == review.getId()){
                r.setTitle(review.getTitle());
                r.setDescription(review.getDescription());
                r.setStars(review.getStars());
                r.setDate(review.getDate());
            }
        }
        itemRepository.save(existingItem);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewToItem(int itemId, Long reviewID) {
        Item existingItem = getItemById(itemId);
        List<Review> itemReviews = existingItem.getReviews();
        Review review = reviewRepository.findById(reviewID).get();
        for (Review eachReview : itemReviews){
            if(eachReview.equals(review)){
                itemReviews.remove(eachReview);
                reviewRepository.delete(review);
            }
        }
        itemRepository.save(existingItem);
    }

    @Override
    public Collection<Item> search(String name) {
        return itemRepository.findByNameContainingIgnoreCase(name);
    }
}
