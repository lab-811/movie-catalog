package com.example.movieinformation.repository;

import com.example.movieinformation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInformationRepository extends JpaRepository<Movie, Long> {
}
