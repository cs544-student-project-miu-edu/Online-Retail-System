package com.example.OnlineRetailsystem.controller;

import com.example.OnlineRetailsystem.domain.Review;
import com.example.OnlineRetailsystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items/{itemID}/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/")
    List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
    @GetMapping("/{id}")
    public void getReviewById(@PathVariable int itemID, @PathVariable Long id) {
        reviewService.getReviewById(id);
    }

    @PostMapping("/newReview")
    public Review createNewReview(@PathVariable int itemID, @RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int itemID, @PathVariable Long id) {
        reviewService.deleteReviewById(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable int itemID, @PathVariable Long id, @RequestBody Review review) {
        return reviewService.updateReviewById(id, review);
    }
}
