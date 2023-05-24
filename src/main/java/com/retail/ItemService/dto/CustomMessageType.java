package com.retail.ItemService.dto;

public class CustomMessageType extends RuntimeException{

    public CustomMessageType(String message) {
        super(message);
    }
}
