package com.retail.ItemService.security;

import com.retail.ItemService.domain.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public String login(@RequestBody Credential credential){
        return authService.login(credential);
    }
    @PostMapping("/authenticate/")
    public String pleaseWork(){
        return "working";
    }
}
