package com.example.moviecatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @JsonIgnore
    private Long id;

    private String nickname;
    private String email;
    private List<String> bookedMovies;
    private List<String> recommendedMovies;
}
