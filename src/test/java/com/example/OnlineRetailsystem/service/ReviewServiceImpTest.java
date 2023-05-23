package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Review;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceImpCreateReviewTest {
    @Test
    void testCreateReview() {
        // Create an instance of ReviewServiceImp
        ReviewService reviewService = new ReviewServiceImp();

        // Call the createReview() method with a sample review
        Review review = new Review(); // Create a sample review
        Review createdReview = reviewService.createReview(review);

        // Assert that the created review is not null
        assertNotNull(createdReview);

        // Add any additional assertions or test cases as needed
    }
}
