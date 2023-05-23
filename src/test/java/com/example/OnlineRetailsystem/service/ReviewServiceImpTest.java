package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Review;
import com.example.OnlineRetailsystem.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceImpTest {


    ReviewRepository reviewRepository;
    ReviewServiceImp reviewServiceImp;
    @BeforeEach
    void setUp() {
        this.reviewRepository = mock(ReviewRepository.class);
        this.reviewServiceImp = new ReviewServiceImp(reviewRepository);
    }

    @Test
    void createReview() {
        Review review = new Review();
        when(reviewRepository.save(review))
                .thenReturn(review);
        Review result = reviewServiceImp.createReview(review);
        assertEquals(review, result);
    }

    @Test
    void deleteReviewById() {
    }

    @Test
    void updateReviewById() {
    }

    @Test
    void getReviewById() {
    }

    @Test
    void getAllReviews() {
    }
}