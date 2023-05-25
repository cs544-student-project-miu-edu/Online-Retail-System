package com.retail.ItemService.repository;

import com.retail.ItemService.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT i.reviews FROM Item i WHERE i.itemID = :itemID")
    List<Review> getReviewFromItem(@Param("itemID") int itemID);
}
