package com.example.moviecatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long account_id;

    private Long movie_id;
}
