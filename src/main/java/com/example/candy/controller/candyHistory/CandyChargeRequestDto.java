package com.example.candy.controller.candyHistory;

import com.example.candy.domain.candy.EventType;
import lombok.Getter;

@Getter
public class CandyChargeRequestDto {
    private final Long userId;
    private final int amount;
    private final EventType eventType;

    CandyChargeRequestDto(Long userId, int amount) {
        this.userId = userId;
        this.amount = amount;
        this.eventType = EventType.CHARGE;
    }
}
