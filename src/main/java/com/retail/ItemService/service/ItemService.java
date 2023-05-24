package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.CompositeItem;
import com.retail.ItemService.domain.Item;
import com.retail.ItemService.domain.LeafItem;
import com.retail.ItemService.dto.CompositeItemResponse;
import com.retail.ItemService.dto.ItemResponse;
import com.retail.ItemService.form.CreateItemForm;
import com.retail.ItemService.form.UpdateItemForm;
import com.retail.ItemService.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper mapper;

    public Item buildItem(CreateItemForm form) {
        if (form.getItems().size() == 0) {
            LeafItem leafItem = new LeafItem(form.getName(), form.getDescription(), form.getPrice(), form.getBarcodeNumber(), form.getQuantity());
            return leafItem;
        }
        CompositeItem compositeItem = new CompositeItem(form.getName(), form.getDescription(), form.getPrice(), form.getBarcodeNumber(), form.getQuantity());
        for (CreateItemForm formItem : form.getItems()) {
            compositeItem.addNestedItem(buildItem(formItem));
        }
        return compositeItem;
    }

    public Item createItems(CreateItemForm form) {
        Item item = buildItem(form);
        return itemRepository.save(item);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(int itemID) {
        return itemRepository.findById(itemID)
                .orElseThrow(() -> new NotFoundException("Item not found with ID: " + itemID));
    }

    public Item updateItem(int itemID, UpdateItemForm form) {
        Item existingItem = getItemById(itemID);
        existingItem.setName(form.getName());
        existingItem.setDescription(form.getDescription());
        existingItem.setPrice(form.getPrice());
        existingItem.setBarcodeNumber(form.getBarcodeNumber());
        existingItem.setQuantity(form.getQuantity());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(int itemID) {
        Item item = getItemById(itemID);
        itemRepository.delete(item);
    }
//    public void removeRelation(int itemID, int childItem){
//
//    }
//    public Item searchRelation(Item parent,Item searching){
//        if(parent.getClass() instanceof CompositeItem){
//
//        }
//    }
}
