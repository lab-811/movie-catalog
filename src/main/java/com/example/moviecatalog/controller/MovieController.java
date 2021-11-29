package com.example.moviecatalog.controller;

import com.example.moviecatalog.model.Account;
import com.example.moviecatalog.model.AccountBookings;
import com.example.moviecatalog.model.AccountRecommendation;
import com.example.moviecatalog.model.CatalogItem;
import com.example.moviecatalog.service.MovieCatalogService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movieCatalog")
public class MovieController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    @GetMapping()
    private List<CatalogItem> getProducts() {
        return movieCatalogService.getMovies();
    }

    @GetMapping("/userData/{userId}")
    public ResponseEntity<?> getAccount(@PathVariable("userId") Long userId) {
        return  ResponseEntity.ok(movieCatalogService.getAccount(userId));
    }

    @GetMapping("/{movieId}")
    private ResponseEntity<?> getMovie(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(movieCatalogService.getMovie(movieId));
    }

    @GetMapping("/login")
    private Account getAccount(@RequestParam String email, @RequestParam String nickname) {


        return movieCatalogService.getAccount(email, nickname );
    }

    @GetMapping("/recommendation")
    private AccountRecommendation getRecommendations(@RequestParam Long accountId) {

        return  movieCatalogService.getRecommendations(accountId);
    }

    @GetMapping("/booking")
    private AccountBookings getBooking(@RequestParam Long accountId) {

        return movieCatalogService.getBooking(accountId);
    }

    @GetMapping("/bookMovie")
    private void bookMovie(@RequestParam Long userId, @RequestParam Long movieId) {

        movieCatalogService.bookMovie(userId, movieId);

    }

    @GetMapping("/recommendMovie")
    private void recMovie(@RequestParam Long userId, @RequestParam Long movieId) {

        movieCatalogService.recommendMovie(userId, movieId);
    }




}
