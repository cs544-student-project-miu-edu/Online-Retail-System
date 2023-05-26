package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.BadCredential;
import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.JwtUtils;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.domain.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public String login(LoginForm form) {
        UserDetails authorizedCustomer = customerService.loadUserByUsername(form.getUsername());
        if (authorizedCustomer == null) {
            throw new BadCredential("customer with this username doesnt exist");
        }
        boolean passwordMatch = passwordEncoder.matches(form.getPassword(), authorizedCustomer.getPassword());
        if (!passwordMatch) {
            throw new BadCredential("Password doesnt match please try again");
        }
        final String accessToken = jwtUtils.generateJwtToken(authorizedCustomer);
        return accessToken;
    }
}
