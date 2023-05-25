package com.retail.ItemService.service;

import com.retail.ItemService.ResponseError.NotFoundException;
import com.retail.ItemService.domain.Customer;
import com.retail.ItemService.domain.Item;
import com.retail.ItemService.domain.Review;
import com.retail.ItemService.dto.ReviewResponse;
import com.retail.ItemService.form.ReviewForm;
import com.retail.ItemService.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    public ReviewResponse createReview(int itemID, ReviewForm form) {
        Item item = itemService.getItemById(itemID);
        Customer customer = customerService.findCustomerId(form.getCustomerID());
        Review review = new Review(form.getTitle(), form.getDescription(), form.getStars(), customer);
        item.getReviews().add(review);
        reviewRepository.saveAll(item.getReviews());
        return mapper.map(review, ReviewResponse.class);
    }

    public List<ReviewResponse> getAllReviewsFromItem(int itemID) {
        return reviewRepository.getReviewFromItem(itemID).stream().map(review -> mapper.map(review, ReviewResponse.class)).toList();
    }

    public Review getReviewByID(int itemID, int reviewID) {
        Review review = reviewRepository.getReviewFromItem(itemID).stream().filter(item -> item.getId() == reviewID).findAny().orElseThrow(() -> {
            throw new NotFoundException("Review not found");
        });
        return review;
    }

    public ReviewResponse getReviewResponse(int itemID, int reviewID) {
        return mapper.map(getReviewByID(itemID, reviewID), ReviewResponse.class);
    }

    public ReviewResponse updateReviewFromItem(int itemID, int reviewID, ReviewForm form) {
        Review review = getReviewByID(itemID, reviewID);
        review.setTitle(form.getTitle());
        review.setDescription(form.getDescription());
        review.setStars(form.getStars());
        reviewRepository.save(review);
        return mapper.map(review, ReviewResponse.class);
    }

    public void deleteReviewFromItem(int itemID, int reviewID) {
        Review review = getReviewByID(itemID, reviewID);
        reviewRepository.delete(review);
    }
}
