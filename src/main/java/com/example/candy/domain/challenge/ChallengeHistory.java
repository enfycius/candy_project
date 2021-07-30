package com.example.candy.domain.challenge;

import com.example.candy.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeHistory {

    @Id
    @GeneratedValue
    @Column(name = "challenge_history_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private int score;
    private int assignedCandy;
    private boolean complete;
    private int tryCount;
    private LocalDateTime completeDate;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

}
