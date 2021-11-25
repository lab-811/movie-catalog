package com.example.moviecatalog.service;

import com.example.moviecatalog.model.Account;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "movie_account", groupId = "group_id")
    public void consume(Account account) {
        System.out.println("account " + account);

    }


}
