package com.retail.ItemService.controller;

import com.retail.ItemService.domain.Review;
import com.retail.ItemService.dto.ReviewResponse;
import com.retail.ItemService.form.ReviewForm;
import com.retail.ItemService.security.UserDetail;
import com.retail.ItemService.service.ReviewService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 * @Todo
 *   security auth user s id must be provided
 * */
@RestController
@RequestMapping("/items/{itemID}/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@PathVariable int itemID, @RequestBody @Valid ReviewForm form) {
        UserDetail userDetails = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reviewService.createReview(itemID, userDetails.getId(), form);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAllReviewsFromItem(@PathVariable int itemID) {
        return reviewService.getAllReviewsFromItem(itemID);
    }

    @GetMapping("/{reviewID}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse getReviewFromItemByID(@PathVariable int itemID, @PathVariable int reviewID) {
        return reviewService.getReviewResponse(itemID, reviewID);
    }

    @PutMapping("/{reviewID}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse updateReviewFromItem(@PathVariable int itemID, @PathVariable int reviewID, @RequestBody ReviewForm form) {
        return reviewService.updateReviewFromItem(itemID, reviewID, form);
    }

    @DeleteMapping("/{reviewID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReviewFromItem(@PathVariable int itemID, @PathVariable int reviewID) {
        reviewService.deleteReviewFromItem(itemID, reviewID);
    }
}
