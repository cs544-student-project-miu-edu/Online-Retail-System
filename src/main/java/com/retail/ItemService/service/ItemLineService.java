package com.retail.ItemService.service;

import com.retail.ItemService.dto.ItemLineResponse;
import com.retail.ItemService.repository.ItemLineRepository;
import com.retail.ItemService.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemLineService {
    @Autowired
    private ItemLineRepository itemLineRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ItemLineResponse getIteLineByOrderWithID(int itemID, int orderID) {
        return modelMapper.map(itemLineRepository.findItemLineByOrder(itemID, orderID), ItemLineResponse.class);
    }


}
