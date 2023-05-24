package com.retail.ItemService.ResponseError;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1231233321123L;

    public NotFoundException(String message) {
        super(message);
    }

    ;

}
