package com.example.candy.repository.challenge;

import com.example.candy.domain.challenge.Challenge;
import com.example.candy.domain.challenge.ChallengeHistory;
import com.example.candy.domain.user.User;
import com.example.candy.enums.Category;
import com.example.candy.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ChallengeHistoryRepositoryTest {

    @Autowired
    ChallengeHistoryRepository challengeHistoryRepository;
    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 챌린지_히스토리_확인() {

        //given
        Challenge challenge = new Challenge();
        challenge.setCategory(Category.ENGLISH);
        challenge.setTitle("5형식");

        User user = User.builder()
                .name("최준혁")
                .email("wnsgur9701@naver.com")
                .build();

        ChallengeHistory challengeHistory = new ChallengeHistory();
        challengeHistory.setUser(user);
        challengeHistory.setChallenge(challenge);

        //when
        challengeRepository.save(challenge);
        userRepository.save(user);
        challengeHistoryRepository.save(challengeHistory);

        //then
        System.out.println("challengeHistory.getId() = " + challengeHistory.getId());

    }

    @Test
    @Transactional
    public void 사용자_챌린지히스토리찾기() {

        // given
        Challenge challenge1 = new Challenge();
        challenge1.setCategory(Category.ENGLISH);
        challenge1.setTitle("5형식");


        Challenge challenge2 = new Challenge();
        challenge2.setCategory(Category.ENGLISH);
        challenge2.setTitle("4형식");


        User user = User.builder()
                .name("최준혁")
                .email("wnsgur9701@naver.com")
                .build();

        ChallengeHistory challengeHistory1 = new ChallengeHistory();
        challengeHistory1.setUser(user);
        challengeHistory1.setChallenge(challenge1);

        ChallengeHistory challengeHistory2 = new ChallengeHistory();
        challengeHistory2.setUser(user);
        challengeHistory2.setChallenge(challenge2);



        //when
        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        userRepository.save(user);
        challengeHistoryRepository.save(challengeHistory1);
        challengeHistoryRepository.save(challengeHistory2);


        //then
        List<ChallengeHistory> userChallengeHistories = challengeHistoryRepository.findAllByUser(user);

        for (ChallengeHistory challengeHistory : userChallengeHistories) {
            System.out.println("challengeHistory.getId() = " + challengeHistory.getId());
            System.out.println("challengeHistory.getUser().getEmail() = " + challengeHistory.getUser().getEmail());
            System.out.println("challengeHistory.getChallenge().getCategory() = " + challengeHistory.getChallenge().getCategory());
        }


    }

}
