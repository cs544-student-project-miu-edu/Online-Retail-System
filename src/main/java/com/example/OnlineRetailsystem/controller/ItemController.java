package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.domain.Review;
import com.example.OnlineRetailsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/item")
public class ItemController{

    @Autowired
    ItemService itemService;


    @GetMapping
    public ResponseEntity<Collection<Item>> getAllItems() {
        Collection<Item> items = itemService.getAllItems();
        return new ResponseEntity<Collection<Item>>(items, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<Item>(createdItem, HttpStatus.OK);
    }


    @PutMapping("/{itemID}")
    public ResponseEntity<Item> updateItem(@PathVariable int itemID, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(itemID, item);
        return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
    }


    @DeleteMapping("/{itemID}")
    public ResponseEntity<Void> deleteItem(@PathVariable int itemID) {
        itemService.deleteItem(itemID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{itemID}")
    public ResponseEntity<Item> getItemByID(@PathVariable int itemID) {
        Item item = itemService.getItemById(itemID);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<Collection<Item>> search(@PathVariable String name) {
        Collection<Item> items = itemService.search(name);
        return new ResponseEntity<Collection<Item>>(items, HttpStatus.OK);
    }


    @GetMapping("/{itemID}/reviews")
    public ResponseEntity<Collection<Review>> getItemReviews(@PathVariable int itemId) {
        Collection<Review> reviews = itemService.getItemReviews(itemId);
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }

    @PostMapping("/{itemID}/review")
    public ResponseEntity<Review> addReviewToItem(@PathVariable int itemID, @RequestBody Review review) {
        Review r = itemService.addReviewToItem(itemID, review);
        return new ResponseEntity<Review>(r, HttpStatus.OK);
    }

    @PutMapping("/{itemID}/review")
    public ResponseEntity<Review> updateReviewToItem(@PathVariable int itemID, @RequestBody Review review) {
        Review r = itemService.updateReviewToItem(itemID, review);
        return new ResponseEntity<Review>(r, HttpStatus.OK);
    }

    @DeleteMapping("/{itemID}/review")
    public ResponseEntity<Void> deleteReviewToItem(@PathVariable int itemId, @RequestBody Long reviewID) {
        itemService.deleteReviewToItem(itemId,reviewID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

}
