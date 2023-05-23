package com.example.OnlineRetailsystem;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.repository.CustomerRepository;
import com.example.OnlineRetailsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OnlineRetailSystemApplication {

//	@Autowired
//	OrderRepository orderRepository;
//
//	@Autowired
//	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineRetailSystemApplication.class, args);
	}



}
