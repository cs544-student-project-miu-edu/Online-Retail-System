package com.example.OnlineRetailsystem.service;


import com.example.OnlineRetailsystem.domain.Review;

import java.util.List;

public interface ReviewService {
    public Review createReview(Review review);
    public void deleteReview(int reviewID);

    public void updateReview(int reviewId, Review review);
    public Review getReview(int reviewId);
    public List<Review> getAllReviews();

}
