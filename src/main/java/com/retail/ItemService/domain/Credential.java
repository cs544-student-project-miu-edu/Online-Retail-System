package com.retail.ItemService.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class Credential {
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Credential(){}

    public Credential(String username, String password){
        this.password= password;
        this.username = username;
    }
}

