package com.example.OnlineRetailsystem.service;


import com.example.OnlineRetailsystem.domain.Review;

import java.util.List;

public interface ReviewService {
    public Review createReview(Review review);
    public void deleteReviewById(Long reviewID);

    public Review updateReviewById(Long reviewId, Review review);

    public List<Review> getAllReviews();
    public Review getReviewById(Long reviewId);

}
