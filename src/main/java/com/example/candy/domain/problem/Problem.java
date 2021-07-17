package com.example.candy.domain.problem;

import com.example.candy.domain.challenge.Challenge;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Problem {

    @Id @GeneratedValue
    @Column(name = "problem_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private int seq;

    private String content;

    private int score;

    private boolean isMultiple;

    private String answer;

    private int multipleAnswer;

    private int multipleCount;

    private LocalDateTime modifiedDate;
}
