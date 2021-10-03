package com.example.moviecatalog.controller;

import com.example.moviecatalog.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieCatalog")
public class MovieController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    @GetMapping("/userData/{userId}")
    public ResponseEntity<?> getAccount(@PathVariable("userId") Long userId) {
        return  ResponseEntity.ok(movieCatalogService.getAccount(userId));
    }

    @GetMapping("/{movieId}")
    private ResponseEntity<?> getMovie(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(movieCatalogService.getMovie(movieId));
    }



}
