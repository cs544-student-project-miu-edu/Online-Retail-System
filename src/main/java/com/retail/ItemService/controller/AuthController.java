package com.retail.ItemService.controller;

import com.retail.ItemService.Utils.JwtUtils;
import com.retail.ItemService.domain.LoginForm;
import com.retail.ItemService.dto.CustomerResponse;
import com.retail.ItemService.dto.LoginResponse;
import com.retail.ItemService.form.CreateCustomerForm;
import com.retail.ItemService.service.AuthService;
import com.retail.ItemService.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerForm form) {
        return customerService.createCustomer(form);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginForm form) {
//        System.out.println(authService.login(form));
        return new LoginResponse(authService.login(form));
    }

}
