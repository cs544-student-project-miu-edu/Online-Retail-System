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
public class CustomerServiceApplication implements CommandLineRunner{
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

    @Override
    public void run(String... args) throws Exception {


        SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Customer buyer = new Customer();
        buyer.setFirstName("John");
        buyer.setLastName("Doe");
        buyer.setEmail("buyer@example.com");
        buyer.setUsername("meaza");
        buyer.setPassword(passwordEncoder.encode("123"));
        buyer.setCustomerType(CustomerType.SELLER);

        customerRepository.save(buyer);

        Credential credential = new Credential("meaza","123");
        credential.setCustomerType(CustomerType.SELLER);
        String token = authService.login(credential);
        System.out.println(token);

        Claims claims = jwtUtil.getAllClaimsFromToken(token);

        System.out.println("subject" + claims.getSubject());
        String customerType = claims.get("customerType",String.class);
        System.out.println(customerType);
        if(customerType.equals(String.valueOf(CustomerType.BUYER)))
            System.out.println("succes");

    }

}
