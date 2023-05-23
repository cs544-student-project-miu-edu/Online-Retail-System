package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemControllerImpl implements ItemController{

    @Autowired
    ItemService itemService;

    @Override
    @GetMapping
    public ResponseEntity<Collection<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<Collection<Item>>(items, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<Item>(createdItem, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{itemID}")
    public ResponseEntity<Item> updateItem(@PathVariable int itemID, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(itemID, item);
        return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{itemID}")
    public ResponseEntity<Void> deleteItem(@PathVariable int itemID) {
        itemService.deleteItem(itemID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Override
    @GetMapping("/{itemID}")
    public ResponseEntity<Item> getItemByID(@PathVariable int itemID) {
        Item item = itemService.getItemById(itemID);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @Override
    @GetMapping("/search/{name}")
    public ResponseEntity<Collection<Item>> search(@PathVariable String name) {
        Collection<Item> items = itemService.search(name);
        return new ResponseEntity<Collection<Item>>(items, HttpStatus.OK);
    }

}
