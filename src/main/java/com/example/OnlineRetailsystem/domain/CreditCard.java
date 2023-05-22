package com.example.OnlineRetailsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creditCards")
public class CreditCard {

    @Id
    @GeneratedValue
    @Column(name = "creditCardID")
    private int id;
    private String cardNumber;
    private LocalDateTime expirationDate;
    private int securityCode;
}
