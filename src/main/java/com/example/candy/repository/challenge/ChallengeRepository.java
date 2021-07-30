package com.example.candy.repository.challenge;

import com.example.candy.domain.challenge.Challenge;
import com.example.candy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
