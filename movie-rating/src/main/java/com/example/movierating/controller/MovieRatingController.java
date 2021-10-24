package com.example.movierating.controller;

import com.example.movierating.model.Rating;
import com.example.movierating.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/movie/ratings")
public class MovieRatingController {

    @Autowired
    private MovieRatingService service;

    @GetMapping("/{movieId}")
    public Optional<Rating> getMovieRating(@PathVariable("movieId") Long movieId) {
        return  service.getMovieRating(movieId);
    }



}
