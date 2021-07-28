package com.example.candy.service.candyHistory;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.domain.user.User;
import com.example.candy.repository.user.UserRepository;
import com.example.candy.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandyHistoryServiceTest {

    @Autowired
    private CandyHistoryService candyHistoryService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeAll
    void setUp() {
        user = User.builder()
                .email("a@naver.com")
                .password("1234")
                .parentPassword("abcd")
                .name("이해석")
                .birth("19950302")
                .phone("01012345678")
                .build();
        userRepository.save(user);
    }

    @Test
    void 가입시_캔디_초기화() {
        CandyHistory candyHistory = candyHistoryService.initCandy(user);
        assertEquals(candyHistory.getUser().getEmail(), user.getEmail());
    }
}