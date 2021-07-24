package com.example.candy.domain.challenge;

import com.example.candy.domain.lecture.Lecture;
import com.example.candy.domain.problem.Problem;
import com.example.candy.enums.Category;
import com.example.candy.enums.Target;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Challenge {

    @Id @GeneratedValue
    @Column(name = "challenge_id")
    private long id;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private List<Problem> problems = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private List<Lecture> lectures = new ArrayList<>();

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    private int totalScore;

    private int requiredScore;

    private int level;

    private Target target;

    private int problem_count;

    private final LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    public Challenge(long id, String title, Category category, String description,
                     int totalScore, int requiredScore, int level, Target target,
                     int problem_count, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.totalScore = totalScore;
        this.requiredScore = requiredScore;
        this.level = level;
        this.target = target;
        this.problem_count = problem_count;
        this.createDate = LocalDateTime.now();
        this.modifiedDate = modifiedDate;
    }

    public Challenge() {
        this.createDate = LocalDateTime.now();
    }
}
