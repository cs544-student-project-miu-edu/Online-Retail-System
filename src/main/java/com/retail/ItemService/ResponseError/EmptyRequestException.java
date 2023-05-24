package com.retail.ItemService.ResponseError;

public class EmptyRequestException extends RuntimeException {
    private static final long serialVersionUID = 1231233321123123L;

    public EmptyRequestException() {
        super("Empty request body");
    }

}
