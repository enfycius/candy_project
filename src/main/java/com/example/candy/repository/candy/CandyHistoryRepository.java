package com.example.candy.repository.candy;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CandyHistoryRepository extends JpaRepository<CandyHistory, Long> {
    Optional<CandyHistory> findTopByUser_IdOrderByCreateDateDesc(Long id);
}
