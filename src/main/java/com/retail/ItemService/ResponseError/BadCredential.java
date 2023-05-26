package com.retail.ItemService.ResponseError;

public class BadCredential extends RuntimeException {
    public BadCredential(String message) {
        super(message);
    }
}
