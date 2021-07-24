package com.example.candy.domain.choice;

import com.example.candy.domain.problem.Problem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Choice {

    @Id @GeneratedValue
    @Column(name = "choice_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private int seq;

    private String content;
}
