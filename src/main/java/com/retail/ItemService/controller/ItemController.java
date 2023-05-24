package com.retail.ItemService.controller;

import com.retail.ItemService.domain.CompositeItem;
import com.retail.ItemService.domain.Item;
import com.retail.ItemService.dto.CompositeItemResponse;
import com.retail.ItemService.dto.ItemResponse;
import com.retail.ItemService.form.CreateItemForm;
import com.retail.ItemService.form.UpdateItemForm;
import com.retail.ItemService.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
/*
 * @Todo
 *   change dto
 * */
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody @Valid CreateItemForm form) {
        return itemService.createItems(form);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems() {
        return itemService.getItems();
    }

    @PutMapping("/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    public Item updateItem(@PathVariable int itemID, @Valid @RequestBody UpdateItemForm form) {
        return itemService.updateItem(itemID, form);
    }

    @GetMapping("/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    public Item getItem(@PathVariable int itemID) {
        return itemService.getItemById(itemID);
    }

    @DeleteMapping("/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable int itemID) {
        itemService.deleteItem(itemID);
    }

}
