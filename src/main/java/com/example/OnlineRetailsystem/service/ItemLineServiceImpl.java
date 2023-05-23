package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.domain.ItemLine;
import com.example.OnlineRetailsystem.dto.CustomMessageType;
import com.example.OnlineRetailsystem.repository.ItemLineRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemLineServiceImpl implements ItemLineService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ItemLineRepository itemLineRepository;

    @Override
    public ItemLine createItemLine(ItemLine itemLine) {
        return itemLineRepository.save(itemLine);
    }

    @Override
    public ItemLine updateItemLine(int itemLineID, ItemLine itemLine) {
        ItemLine existingItemLine = modelMapper.map(getItemLineById(itemLineID), ItemLine.class);
        existingItemLine.setItem(itemLine.getItem());
        existingItemLine.setQuantity(itemLine.getQuantity());
        existingItemLine.setDiscountValue(itemLine.getDiscountValue());
        return itemLineRepository.save(existingItemLine);
    }

    @Override
    public void deleteItemLine(int itemLineID) {
        itemLineRepository.deleteById(itemLineID);
    }

    @Override
    public List<ItemLine> getAllItemLines() {
        return itemLineRepository.findAll();
    }

    @Override
    public ItemLine getItemLineById(int itemLineID) {
        return itemLineRepository.findById(itemLineID)
                .orElseThrow(() -> new CustomMessageType("Item Line not found with ID: " + itemLineID));
    }

    public void applyDiscount(int itemLineID, double discount) {
        ItemLine itemLine = itemLineRepository.findById(itemLineID)
                .orElseThrow(() -> new CustomMessageType("Item Line not found with ID: " + itemLineID));
        System.out.println("Items price was "+ itemLine.getItem().getPrice() * itemLine.getQuantity());
        Item item = itemLine.getItem();
        double discountPrice = item.getPrice() - (item.getPrice() * discount);
        item.setPrice(discountPrice);
        System.out.println("Items price is "+ itemLine.getItem().getPrice() * itemLine.getQuantity());
        itemLine.setItem(item);
        itemLineRepository.save(itemLine);
    }
}
