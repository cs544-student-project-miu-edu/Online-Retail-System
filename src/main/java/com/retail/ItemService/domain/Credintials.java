package com.retail.ItemService.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Credintials {
    String username;
    String password;

    public Credintials(){}

    public Credintials(String username, String password){
        this.password= password;
        this.username = username;
    }
}

