package com.example.candy.service.candyHistory;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.domain.candy.EventType;
import com.example.candy.domain.user.User;
import com.example.candy.repository.candy.CandyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandyHistoryService {

    @Autowired
    private CandyHistoryRepository candyHistoryRepository;

    @Transactional
    public CandyHistory initCandy(User user) {
        CandyHistory candyHistory = CandyHistory.builder()
                .totalCandy(0)
                .user(user)
                .eventType(EventType.INIT)
                .parentCandy(0)
                .studentCandy(0)
                .amount(0)
                .build();

        return save(candyHistory);
    }

    public CandyHistory save(CandyHistory candyHistory) {
        return candyHistoryRepository.save(candyHistory);
    }
}
