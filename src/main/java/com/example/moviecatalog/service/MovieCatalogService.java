package com.example.moviecatalog.service;


import com.example.moviecatalog.model.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    MovieRating movieRating;

    @Autowired
    MovieItem movieItem;

    @Autowired
    MovieAccountRecommendation movieAccountRecommendation;

    @Autowired
    AccountBookingItem accountBookingItem;

    @Autowired
    AccountItem accountItem;

    public CatalogItem getMovie(Long movieId) {

        Movie movie = movieItem.getMovieItem(movieId);

        Rating rating = movieRating.getRating(movieId);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setId(movieId);
        catalogItem.setName(movie.getName());
        catalogItem.setImage(movie.getImage());
        catalogItem.setDescription(movie.getDescription());
        catalogItem.setRating(rating.getRating());

        return catalogItem;
    }




    public Account getAccount(Long userId) {

        List<String> bookingList = new ArrayList<>();

        List<String> recommendationList = new ArrayList<>();

        Account account = accountItem.getAccountItem(userId);

        AccountBooking booking = accountBookingItem.getAccountBookingItem(userId);

        AccountRecommendation recommendation = movieAccountRecommendation.getAccountRecommendation(userId);

        for (Booking list : booking.getBookings()) {


            Movie movie = movieItem.getMovieItem(list.getMovie_id());

            bookingList.add(movie.getName());
        }

        for (Recommendation list : recommendation.getRecommendations()) {

            Movie movie = movieItem.getMovieItem(list.getMovie_id());

            recommendationList.add(movie.getName());
        }

        account.setBookedMovies(bookingList);

        account.setRecommendedMovies(recommendationList);

        return account;
    }



}
