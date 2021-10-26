package com.example.moviecatalog.service;

import com.example.moviecatalog.model.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountItem {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackAccountItem")
    public Account getAccountItem(Long userId) {
        return restTemplate.getForObject("http://movie-account/user/account/" + userId, Account.class);
    }


    public Account getFallbackAccountItem(Long userId) {

        Account account = new Account();
        account.setId(userId);
        account.setNickname("NO USER FOUND");
        account.setEmail("NO EMAIL FOUND");
        return account;
    }
}
