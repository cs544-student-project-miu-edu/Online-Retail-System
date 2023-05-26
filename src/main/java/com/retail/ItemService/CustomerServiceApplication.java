package com.retail.ItemService;

import com.retail.ItemService.domain.Address;
import com.retail.ItemService.domain.AddressType;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.domain.CustomerType;
import com.retail.ItemService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String args[]) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("12", "123", "IA", "52557", AddressType.SHIPPINGADDRESS);
        Customer admin = new Customer("Server", "Admin", "admin@mail.com", address, address);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setCustomerType(CustomerType.SELLER);
        customerRepository.save(admin);
    }
}
