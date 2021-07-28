package com.example.candy.service.candyHistory;

import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.repository.candy.CandyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandyHistoryService {

    @Autowired
    private CandyHistoryRepository candyHistoryRepository;

    public CandyHistory initCandy() {
        CandyHistory candyHistory = CandyHistory.builder()
                .totalCandy()
                .parentCandy()
                .studentCandy()

                .build();
    }

    public CandyHistory save(CandyHistory candyHistory) {
        return candyHistoryRepository.save(candyHistory);
    }
}
