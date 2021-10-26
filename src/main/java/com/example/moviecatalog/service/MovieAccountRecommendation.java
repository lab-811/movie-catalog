package com.example.moviecatalog.service;

import com.example.moviecatalog.model.AccountRecommendation;
import com.example.moviecatalog.model.Recommendation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieAccountRecommendation {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackRecommendation")
    public AccountRecommendation getAccountRecommendation(Long userId) {
        return restTemplate.getForObject("http://movie-recommendation/recommendationsData/" + userId, AccountRecommendation.class);
    }


    public AccountRecommendation getFallbackRecommendation(Long userId) {

        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation(0L, 0L, 0L));

        return new AccountRecommendation(recommendations);
    }
}
