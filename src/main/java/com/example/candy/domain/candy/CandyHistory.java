package com.example.candy.domain.candy;

import com.example.candy.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CandyHistory {

    @Id
    @GeneratedValue
    @Column(name = "candy_histyory_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int totalCandy;
    private int parentCandy;
    private int studentCandy;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private int amount;
    private LocalDateTime createDate;

    public void addUser(User user) {
        this.user = user;
        System.out.println(this);
        System.out.println(this.user.getCandyHistories());
        System.out.println(user);
        System.out.println(user.getEmail());

        user.getCandyHistories().add(this);
    }
}
