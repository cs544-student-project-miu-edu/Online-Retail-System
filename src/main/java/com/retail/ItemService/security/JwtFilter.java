package com.retail.ItemService.security;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.Utils.JwtUtil;
import com.retail.ItemService.controller.ControllerExceptionHandler;
import com.retail.ItemService.domain.Credential;
import com.retail.ItemService.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {



    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;
        Claims claims;
        String customerType = null;
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                claims = jwtUtil.getAllClaimsFromToken(token);
                username = claims.getSubject();
                customerType = claims.get("customerType",String.class);
                GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(customerType));
                grantedAuthoritys.add(authority);

            } catch (ExpiredJwtException e) {

                throw new NotFoundException("Not authorized to access this resource");
            }
        }

        if (username != null && jwtUtil.validateToken(token)) {

            var customer = customerRepository.findCustomerByUsername(username);
            Credential credential = new Credential(customer.getUsername(), customer.getPassword());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    credential, null, grantedAuthoritys);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


}

