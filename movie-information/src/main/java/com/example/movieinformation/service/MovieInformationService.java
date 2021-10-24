package com.example.movieinformation.service;

import com.example.movieinformation.model.Movie;
import com.example.movieinformation.repository.MovieInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieInformationService {

    @Autowired
    private MovieInformationRepository repository;


    public Optional<Movie> getMovie(Long movieId) {
        return repository.findById(movieId);
    }
}
