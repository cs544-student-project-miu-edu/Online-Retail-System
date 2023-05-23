package com.example.OnlineRetailsystem.repository;

import com.example.OnlineRetailsystem.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
