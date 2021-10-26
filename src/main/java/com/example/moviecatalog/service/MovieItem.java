package com.example.moviecatalog.service;

import com.example.moviecatalog.model.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieItem {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieItem")
    public Movie getMovieItem(Long movieId) {
        return restTemplate.getForObject("http://movie-information/movies/" + movieId, Movie.class);
    }


    public Movie getFallbackMovieItem(Long movieId) {

        return  new Movie(movieId, "No Movie Name Found", "No Image Found", "No description found");
    }
}
