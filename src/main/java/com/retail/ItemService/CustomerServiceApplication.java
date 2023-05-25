package com.retail.ItemService;

import com.retail.ItemService.Utils.JwtUtil;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.domain.CustomerType;
import com.retail.ItemService.domain.Credential;
import com.retail.ItemService.repository.CustomerRepository;
import com.retail.ItemService.security.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;

@SpringBootApplication
public class CustomerServiceApplication{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthService authService;

    public static void main(String args[]) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
