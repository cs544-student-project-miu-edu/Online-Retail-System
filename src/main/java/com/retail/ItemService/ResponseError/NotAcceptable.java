package com.retail.ItemService.ResponseError;

public class NotAcceptable extends RuntimeException {
    public NotAcceptable(String message) {
        super(message);
    }
}
