package com.example.movierecommendation.service;

import com.example.movierecommendation.model.AccountRecommendation;
import com.example.movierecommendation.model.Recommendation;
import com.example.movierecommendation.repository.MovieRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRecommendationService {

    @Autowired
    private MovieRecommendationRepository repository;

    public AccountRecommendation getUserRecommendations(Long userId) {
        List<Recommendation> recommendations = repository.findAllUserRecommendations(userId);

        AccountRecommendation accountBookings = new AccountRecommendation();
        accountBookings.setRecommendations(recommendations);
        return accountBookings;
    }
}
