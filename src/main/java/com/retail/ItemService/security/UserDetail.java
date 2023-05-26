package com.retail.ItemService.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.domain.CustomerType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetail implements UserDetails {
    private String username;

    @JsonIgnore
    private String password;
    private int id;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(int id, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetail build(Customer customer) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (customer.getCustomerType().name().equals(CustomerType.SELLER.name())) {
            authorities.add(new SimpleGrantedAuthority(CustomerType.BUYER.name()));
        }
        authorities.add(new SimpleGrantedAuthority(customer.getCustomerType().name()));
        authorities.add(new SimpleGrantedAuthority("" + customer.getId()));
        System.out.println(authorities);
        return new UserDetail(customer.getId(), customer.getUsername(), customer.getPassword(), authorities);
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetail user = (UserDetail) o;
        return Objects.equals(id, user.id);
    }

}
