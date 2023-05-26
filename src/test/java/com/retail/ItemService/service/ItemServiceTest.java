package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.CompositeItem;
import com.retail.ItemService.domain.Item;
import com.retail.ItemService.domain.LeafItem;
import com.retail.ItemService.form.UpdateItemForm;
import com.retail.ItemService.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    @TestConfiguration
    static class ItemServiceImplTestContextConfiguration {
        @Bean
        public ItemService itemService() {
            return new ItemService();
        }
    }

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void whenValidItemIDThenLeafItemShouldBeFound(){
        int itemID = 1;
        LeafItem singleItem = new LeafItem("Leaf Item","leaf item description",10.0 ,"11111111",5);
        singleItem.setItemID(itemID);

        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(singleItem));
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(singleItem);

        Item found = itemService.getItemById(itemID);
        assertNotNull(found);
        assertEquals(found.getItemID(),itemID);
    }

    @Test
    public void whenValidItemIDThenCompositeItemShouldBeFound(){
        int itemID = 4;
        LeafItem compItem1 = new LeafItem("composite Item 1","composite description 1",50.0,"222222",3);
        compItem1.setItemID(2);
        LeafItem compItem2 = new LeafItem("composite Item 2","composite description 2",50.0,"11111111",3);
        compItem2.setItemID(3);
        CompositeItem compositeItem = new CompositeItem();
        compositeItem.setItemID(itemID);
        compositeItem.addNestedItem(compItem1);
        compositeItem.addNestedItem(compItem2);

        Mockito.when(itemRepository.findById(itemID)).thenReturn(Optional.of(compositeItem));
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(compositeItem);

        Item found = itemService.getItemById(itemID);

        assertNotNull(found);
        assertEquals(found.getItemID(),itemID);
    }

//    @Test
//    public void whenItemIdAndUpdateFormThenItemShouldBeUpdated(){
//        int itemID = 1;
//        LeafItem singleItem = new LeafItem("Leaf Item","leaf item description",10.0 ,"11111111",5);
//        singleItem.setItemID(itemID);
//
//        LeafItem updateItem = new LeafItem("update Item","update leaf item description",10.0 ,"11111111",5);
//
//        UpdateItemForm updateItemForm = modelMapper.map(updateItem, UpdateItemForm.class);
//        Mockito.when(itemRepository.findById(itemID)).thenReturn(Optional.of(singleItem));
//        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(singleItem);
//
//        Item found = itemService.updateItem(itemID,updateItemForm);
//        assertNotNull(found);
//        assertEquals(found,updateItemForm);
//    }
    @Test
    public void whenInvalidItemIDThenItemShouldNotBeFound(){
        int itemID = 2;
        Mockito.when(itemRepository.findById(itemID)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> itemService.getItemById(itemID));
    }

    @Test
    public void whenValidItemIDThenItemShouldBeDeleted() {
        int itemID = 1;
        LeafItem item = new LeafItem("Leaf Item", "leaf item description", 10.0, "11111111", 5);
        item.setItemID(itemID);

        Mockito.when(itemRepository.findById(itemID)).thenReturn(Optional.of(item));
        itemService.deleteItem(itemID);
        Mockito.verify(itemRepository, Mockito.times(1)).delete(item);
    }
    @Test
    public void whenInvalidItemIDThenItemShouldNotBeDeleted() {
        int itemID = 2;
        Mockito.when(itemRepository.findById(itemID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itemService.deleteItem(itemID));

        Mockito.verify(itemRepository, Mockito.never()).delete(Mockito.any(Item.class));
    }

}
