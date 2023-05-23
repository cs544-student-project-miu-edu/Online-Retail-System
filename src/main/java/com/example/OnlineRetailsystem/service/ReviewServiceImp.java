package com.example.OnlineRetailsystem.service;

import com.example.OnlineRetailsystem.domain.Item;
import com.example.OnlineRetailsystem.domain.Review;
import com.example.OnlineRetailsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review createReview(Review review) {
 return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long reviewID) {
     reviewRepository.deleteById(reviewID);
    }


    @Override
    public Review updateReviewById(Long reviewId, Review review) {
        // Find the review with the specified reviewId
        Review reviewToUpdate = getReviewById(reviewId);

        // Update the review if found
        if (reviewToUpdate != null) {
            // Update the review details
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setStars(review.getStars());
            reviewToUpdate.setDate(review.getDate());
            reviewToUpdate.setBuyer(review.getBuyer());

            System.out.println("Review with ID " + reviewId + " has been updated.");
        } else {
            System.out.println("Review with ID " + reviewId + " was not found.");
        }
        return reviewToUpdate;
    }



    public Review getReviewById(Long reviewId) {
        Optional<Review> review= reviewRepository.findById(reviewId);
        if (review.isEmpty()){
            return null;
        }
        return review.get();
    }


    @Override
    public List<Review> getAllReviews() {
     return reviewRepository.findAll();
    }

}