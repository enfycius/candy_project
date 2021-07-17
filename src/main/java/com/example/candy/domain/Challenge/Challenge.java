package com.example.candy.domain.Challenge;

import com.example.candy.enums.Category;
import com.example.candy.enums.Target;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Challenge {

    @Id @GeneratedValue
    @Column(name = "challenge_id")
    private long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    private int totalScore;

    private int requiredScore;

    private int level;

    private Target target;

    private final LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    public Challenge(long id, String title, Category category, String description,
                     int totalScore, int requiredScore, int level, Target target,
                     LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.totalScore = totalScore;
        this.requiredScore = requiredScore;
        this.level = level;
        this.target = target;
        this.createDate = LocalDateTime.now();
        this.modifiedDate = modifiedDate;
    }

    public Challenge() {
        this.createDate = LocalDateTime.now();
    }
}
