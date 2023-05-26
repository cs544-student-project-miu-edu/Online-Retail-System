package com.retail.ItemService.dto;

import lombok.Data;

@Data
public class LoginResponse {
    String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
