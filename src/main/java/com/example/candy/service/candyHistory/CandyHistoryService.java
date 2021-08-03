package com.example.candy.service.candyHistory;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.domain.candy.EventType;
import com.example.candy.domain.user.User;
import com.example.candy.repository.candy.CandyHistoryRepository;
import com.example.candy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CandyHistoryService {

    @Autowired
    private CandyHistoryRepository candyHistoryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public CandyHistory initCandy(User user) {
        CandyHistory candyHistory = CandyHistory.builder()
                .totalCandy(0)
                .eventType(EventType.INIT)
                .parentCandy(0)
                .studentCandy(0)
                .amount(0)
                .user(user)
                .createDate(LocalDateTime.now())
                .build();
        CandyHistory savedCandyHistory = save(candyHistory);
        savedCandyHistory.addUser(user);
        return savedCandyHistory;
    }

    @Transactional
    public CandyHistory chargeCandy(Long userId, int amount) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("no such userId"));

        CandyHistory latestCandy = findLatestOne(userId);
        CandyHistory candyHistory = CandyHistory.builder()
                .totalCandy(latestCandy.getTotalCandy() + amount)
                .eventType(EventType.CHARGE)
                .parentCandy(latestCandy.getParentCandy() + amount)
                .studentCandy(latestCandy.getStudentCandy())
                .amount(amount)
                .createDate(LocalDateTime.now())
                .user(user)
                .build();
        return save(candyHistory);
    }

    public CandyHistory findLatestOne(Long userId) { return candyHistoryRepository.findTopByUser_IdOrderByCreateDateDesc(userId); }

    public CandyHistory save(CandyHistory candyHistory) {
        return candyHistoryRepository.save(candyHistory);
    }
}
