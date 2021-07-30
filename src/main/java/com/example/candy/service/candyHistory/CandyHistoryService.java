package com.example.candy.service.candyHistory;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.domain.candy.EventType;
import com.example.candy.domain.user.User;
import com.example.candy.repository.candy.CandyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CandyHistoryService {

    @Autowired
    private CandyHistoryRepository candyHistoryRepository;

    @Transactional
    public CandyHistory initCandy(User user) {
        CandyHistory candyHistory = CandyHistory.builder()
                .totalCandy(0)
                .eventType(EventType.INIT)
                .parentCandy(0)
                .studentCandy(0)
                .amount(0)
                .createDate(LocalDateTime.now())
                .build();
        CandyHistory savedCandyHistory = save(candyHistory);
        savedCandyHistory.addUser(user);
        return savedCandyHistory;
    }

    public CandyHistory save(CandyHistory candyHistory) {
        return candyHistoryRepository.save(candyHistory);
    }
}
