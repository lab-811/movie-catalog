package com.example.movierecommendation.controller;

import com.example.movierecommendation.service.MovieRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendationsData")
public class MovieRecommendationController {

    @Autowired
    private MovieRecommendationService service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBookingData(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(service.getUserRecommendations(userId));
    }

}
