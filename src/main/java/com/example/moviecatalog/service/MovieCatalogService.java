package com.example.moviecatalog.service;


import com.example.moviecatalog.model.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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


    public List<CatalogItem> getMovies() {
        List<CatalogItem> catalogList = new ArrayList<>();
        List<Rating> ratingsList = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        int i = 0;


        ResponseEntity<List<Rating>> rateResponse =
                restTemplate.exchange("http://movie-rating/movie/ratings",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {
                        });

        ResponseEntity<List<Movie>> productResponse =
                restTemplate.exchange("http://movie-information/movies/",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
                        });

        ratingsList = rateResponse.getBody();
        movies = productResponse.getBody();

        for(Movie product : movies) {


            catalogList.add(new CatalogItem(product.getId(),
                    product.getName(),
                    product.getImage(),
                    product.getDescription(),
                    ratingsList.get(i).getRating()));

            i++;
        }





        return catalogList;
    }

    public Account getAccount(String email, String nicname) {



        return restTemplate.getForObject("http://movie-account/user/account/login?email=" + email + "&nickname=" + nicname, Account.class);

    }

    public  AccountRecommendation getRecommendations(Long accountId) {

        return  restTemplate.getForObject("http://movie-recommendation/recommendationsData/" + accountId, AccountRecommendation.class);
    }

    public  AccountBookings getBooking(Long accountId) {

        return  restTemplate.getForObject("http://movie-booking/bookingData/" + accountId, AccountBookings.class);
    }

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


    public void bookMovie(Long userId, Long movieId) {

        restTemplate.getForObject("http://movie-booking/bookingData?userId=" + userId + "&movieId=" + movieId, String.class);
    }

    public void recommendMovie(Long userId, Long movieId) {

        restTemplate.getForObject("http://movie-recommendation/recommendationsData?userId=" + userId + "&movieId=" + movieId, String.class);

    }





    public Account getAccount(Long userId) {

        List<String> bookingList = new ArrayList<>();

        List<String> recommendationList = new ArrayList<>();

        Account account = accountItem.getAccountItem(userId);

        AccountBookings booking = accountBookingItem.getAccountBookingItem(userId);

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
