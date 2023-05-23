package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.ItemLine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemLineController {

    ResponseEntity<ItemLine> createItemLine(ItemLine itemLine);
    ResponseEntity<ItemLine> updateItemLine(int itemLineId, ItemLine itemLine);
    ResponseEntity<Void> deleteItemLine(int itemLineId);
    ResponseEntity<List<ItemLine>> getAllItemLines();
    ResponseEntity<ItemLine> getItemLineById(int itemLineId);
}
