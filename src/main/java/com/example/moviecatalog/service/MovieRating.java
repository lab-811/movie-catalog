package com.example.moviecatalog.service;

import com.example.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieRating {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackRating")
    public Rating getRating(Long movieId) {
        return restTemplate.getForObject("http://movie-rating/movie/ratings/" + movieId, Rating.class);
    }

    public Rating getFallbackRating(Long movieId) {

        return  new Rating(movieId, 0.0);
    }
}
