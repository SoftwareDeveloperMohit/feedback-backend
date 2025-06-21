package com.example.feedback_backend.repository;

import com.example.feedback_backend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByEmail(String email);
}
