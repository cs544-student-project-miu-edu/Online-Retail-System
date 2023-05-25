package com.retail.ItemService.controller;

import com.retail.ItemService.dto.ItemLineResponse;
import com.retail.ItemService.service.ItemLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/{orderID}/itemLine")
public class ItemLineController {
    @Autowired
    private ItemLineService itemLineService;

    @GetMapping("/{lineID}")
    public ItemLineResponse getItemLineByID(@PathVariable int orderID, @PathVariable int lineID) {
        return itemLineService.getIteLineByOrderWithID(lineID, orderID);
    }
}
