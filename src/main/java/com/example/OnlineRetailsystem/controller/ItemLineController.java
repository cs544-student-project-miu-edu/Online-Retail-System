package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.ItemLine;
import com.example.OnlineRetailsystem.service.ItemLineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemline")
public class ItemLineController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ItemLineService itemLineService;

    @PostMapping
    public ResponseEntity<ItemLine> createItemLine(@RequestBody ItemLine itemLine) {
        ItemLine createdItemLine = itemLineService.createItemLine(itemLine);
        return new ResponseEntity<ItemLine>(createdItemLine, HttpStatus.OK);
    }

    @PutMapping("/{itemLineId}")
    public ResponseEntity<ItemLine> updateItemLine(@PathVariable int itemLineId, @RequestBody ItemLine itemLine) {
        ItemLine updatedItemLine = itemLineService.updateItemLine(itemLineId, itemLine);
        return new ResponseEntity<ItemLine>(updatedItemLine, HttpStatus.OK);
    }

    @DeleteMapping("/{itemLineId}")
    public ResponseEntity<Void> deleteItemLine(@PathVariable int itemLineId) {
        ItemLine deletedItemLine = modelMapper.map(itemLineService.getItemLineById(itemLineId), ItemLine.class);
        System.out.println("Deleted: "+ deletedItemLine);
        itemLineService.deleteItemLine(itemLineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ItemLine>> getAllItemLines() {
        List<ItemLine> itemLines = itemLineService.getAllItemLines();
        return new ResponseEntity<List<ItemLine>>(itemLines, HttpStatus.OK);
    }

    @GetMapping("/{itemLineId}")
    public ResponseEntity<ItemLine> getItemLineById(@PathVariable int itemLineId) {
        ItemLine itemLine = itemLineService.getItemLineById(itemLineId);
        return new ResponseEntity<ItemLine>(itemLine, HttpStatus.OK);
    }
}