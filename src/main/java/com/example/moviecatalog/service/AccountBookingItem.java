package com.example.moviecatalog.service;

import com.example.moviecatalog.model.AccountBooking;
import com.example.moviecatalog.model.Booking;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountBookingItem {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackAccountBooking")
    public AccountBooking getAccountBookingItem(Long userId) {
        return restTemplate.getForObject("http://movie-booking/bookingData/" + userId, AccountBooking.class);
    }

    public AccountBooking getFallbackAccountBooking(Long userId) {
        List<Booking> bookings = new ArrayList<>();

        bookings.add(new Booking(0L, userId, 0L));

        return  new AccountBooking(bookings);
    }
}
