package com.retail.ItemService.security;

import com.retail.ItemService.Utils.JwtUtil;
import com.retail.ItemService.domain.Credential;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public String login(Credential credential) {
        Customer authorizedCustomer = customerRepository.findCustomerByUsername(credential.getUsername());
        if(authorizedCustomer!=null){
            boolean passwordMath = passwordEncoder.matches(credential.getPassword(), authorizedCustomer.getPassword());
            if(passwordMath){
                credential.setCustomerType(authorizedCustomer.getCustomerType());
                final String accessToken = jwtUtil.generateToken(credential);
                return accessToken;
            }else{
                throw new BadCredentialsException("Password doesnt match please try again");
            }
        }else{
            throw new BadCredentialsException("customer with this username doenst exist");
        }
    }
}
