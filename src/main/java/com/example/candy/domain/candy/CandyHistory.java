package com.example.candy.domain.candy;

import com.example.candy.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandyHistory {

    @Id
    @GeneratedValue
    @Column(name = "candy_histyory_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long totalCandy;
    private Long parentCandy;
    private Long studentCandy;

}
