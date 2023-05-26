package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.ItemLine;
import com.retail.ItemService.dto.ItemLineResponse;
import com.retail.ItemService.repository.ItemLineRepository;
import com.retail.ItemService.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemLineService {
    @Autowired
    private ItemLineRepository itemLineRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ItemLineResponse getIteLineByOrderWithID(int itemID, int orderID) {
        ItemLine itemLine = itemLineRepository.findItemLineByOrder(itemID, orderID).orElseThrow(() -> {
            throw new NotFoundException("Not found");
        });
        return new ItemLineResponse(itemLine);
    }


}
