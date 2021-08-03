package com.example.candy.repository.candy;

import com.example.candy.domain.candy.CandyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandyHistoryRepository extends JpaRepository<CandyHistory, Long> {
    CandyHistory findTopByUser_IdOrderByCreateDateDesc(Long id);
}
