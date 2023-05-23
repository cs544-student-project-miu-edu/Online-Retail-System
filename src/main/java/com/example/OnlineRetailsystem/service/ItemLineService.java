package com.example.OnlineRetailsystem.service;


import com.example.OnlineRetailsystem.domain.ItemLine;

import java.util.List;

public interface ItemLineService {

    ItemLine createItemLine(ItemLine itemLine);
    ItemLine updateItemLine(int itemLineId, ItemLine itemLine);
    void deleteItemLine(int itemLineId);
    List<ItemLine> getAllItemLines();
    ItemLine getItemLineById(int itemLineId);

}
