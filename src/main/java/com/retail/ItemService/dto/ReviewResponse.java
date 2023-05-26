package com.retail.ItemService.dto;

import com.retail.ItemService.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewResponse {
    private int id;
    private String title;
    private String description;
    private int stars;
    private LocalDateTime date;

    public ReviewResponse() {
    }

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.stars = review.getStars();
        this.date = review.getDate();
    }
}
