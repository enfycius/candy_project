package com.example.candy.service.challenge;

import com.example.candy.domain.challenge.Challenge;
import com.example.candy.domain.challenge.ChallengeHistory;
import com.example.candy.domain.user.User;
import com.example.candy.enums.Category;
import com.example.candy.error.NotFoundException;
import com.example.candy.repository.challenge.ChallengeHistoryRepository;
import com.example.candy.repository.challenge.ChallengeRepository;
import com.example.candy.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeHistoryRepository challengeHistoryRepository;

    public ChallengeService(UserRepository userRepository, ChallengeRepository challengeRepository, ChallengeHistoryRepository challengeHistoryRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.challengeHistoryRepository = challengeHistoryRepository;
    }


    // 필요한 것. user id
    @Transactional(readOnly = true)
    public List<ChallengeHistory> findChallengeHistories(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        List<ChallengeHistory> challengeHistories = challengeHistoryRepository.findAllByUser(user);
        return challengeHistories;

    }

    @Transactional(readOnly = true)
    public List<ChallengeHistory> categoryChallengeHistories(Long userId, Category category) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        List<ChallengeHistory> challengeHistory = challengeHistoryRepository.findAllByUserAndCategory(user, category);
        return challengeHistory;
    }

    public Long registerChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
        return challenge.getId();
    }


}
