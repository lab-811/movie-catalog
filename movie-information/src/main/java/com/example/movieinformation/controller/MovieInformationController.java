package com.example.movieinformation.controller;

import com.example.movieinformation.model.Movie;
import com.example.movieinformation.service.MovieInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieInformationController {

    @Autowired
    private MovieInformationService service;

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovie(@PathVariable("movieId") Long movieId) {
        return  service.getMovie(movieId);
    }
}
