package com.example.candy.domain.problem;

import com.example.candy.domain.challenge.ChallengeHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemHistory {

    @Id
    @GeneratedValue
    @Column(name = "problem_history_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "challenge_history_id")
    private ChallengeHistory challengeHistory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private boolean isSuccess;
    private String answer;
    private int multipleAnswer;

}
