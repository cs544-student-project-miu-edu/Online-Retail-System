package com.retail.ItemService.dto;

import com.retail.ItemService.domain.Item;
import lombok.Data;

@Data
public class IndividualItemResponse extends ItemResponse {
    public IndividualItemResponse(Item item) {
        super(item);
    }
}
