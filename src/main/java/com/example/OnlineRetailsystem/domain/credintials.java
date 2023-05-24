package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class credintials {
    String username;
    String password;
    public credintials(){}
    public credintials(String username, String password){
        this.password= password;
        this.username = username;
    }
}

