package com.retail.ItemService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int stars;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "buyerID")
    private Customer buyer;

    public Review() {
    }

    public Review(String title, String description, int stars,Customer buyer) {
        this.title = title;
        this.description = description;
        this.stars = stars;
        this.date = LocalDateTime.now();
        this.buyer = buyer;
    }

}