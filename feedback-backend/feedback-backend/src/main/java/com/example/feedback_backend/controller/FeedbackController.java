package com.example.feedback_backend.controller;


import com.example.feedback_backend.model.Feedback;
import com.example.feedback_backend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @PostMapping("/feedback")
    public ResponseEntity<String> saveFeedback(@RequestBody Feedback feedback) {
        if (feedback.getName().isBlank() || feedback.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Name and Email are required.");
        }

        if (feedbackRepository.findByEmail(feedback.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Feedback already submitted.");
        }

        feedbackRepository.save(feedback);
        return ResponseEntity.ok("Feedback saved successfully.");
    }

    @GetMapping("/feedback")
    public List<Feedback> getFeedback() {
        return feedbackRepository.findAll();
    }
}
