package com.example.movierecommendation.repository;

import com.example.movierecommendation.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query(value = "SELECT * FROM recommendation b WHERE b.account_id=?1", nativeQuery = true)
    List<Recommendation> findAllUserRecommendations(Long id);

}
