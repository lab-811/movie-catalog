package com.example.movierecommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieRecommendationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRecommendationApplication.class, args);
    }

}
