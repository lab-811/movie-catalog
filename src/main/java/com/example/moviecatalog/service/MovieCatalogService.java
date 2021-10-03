package com.example.moviecatalog.service;


import com.example.moviecatalog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    public CatalogItem getMovie(Long movieId) {

        Movie movie = restTemplate.getForObject("http://movie-information/movies/" + movieId, Movie.class);

        Rating rating = restTemplate.getForObject("http://movie-rating/movie/ratings/" + movieId, Rating.class);

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setId(movieId);
        catalogItem.setName(movie.getName());
        catalogItem.setDescription(movie.getDescription());
        catalogItem.setRating(rating.getRating());

        return catalogItem;
    }

    public Account getAccount(Long userId) {

        List<String> bookingList = new ArrayList<>();

        List<String> recommendationList = new ArrayList<>();

        Account account = restTemplate.getForObject("http://movie-account/user/account/" + userId, Account.class);

        AccountBooking booking = restTemplate.getForObject("http://movie-booking/bookingData/" + userId, AccountBooking.class);

        AccountRecommendation recommendation = restTemplate.getForObject("http://movie-recommendation/recommendationsData/" + userId, AccountRecommendation.class);

        for (Booking list : booking.getBookings()) {

            Movie movie = restTemplate.getForObject("http://movie-information/movies/" + list.getMovie_id(), Movie.class);

            bookingList.add(movie.getName());
        }

        for (Recommendation list : recommendation.getRecommendations()) {

            Movie movie = restTemplate.getForObject("http://movie-information/movies/" + list.getMovie_id(), Movie.class);

            recommendationList.add(movie.getName());
        }

        account.setBookedMovies(bookingList);

        account.setRecommendedMovies(recommendationList);

        return account;
    }

}
