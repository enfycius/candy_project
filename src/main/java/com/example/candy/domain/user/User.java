package com.example.candy.domain.user;


import com.example.candy.domain.candy.CandyHistory;
import com.example.candy.security.Jwt;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import  javax.validation.constraints.Email;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CandyHistory> candyHistories;

    @Email
    private String email;

    private String name;
    private String password;
    private String parentPassword;
    private String phone;
    private String birth;
    private boolean enabled;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createDate;
    
    private int parentCandy;
    private int studentCandy;

    @Transient
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void afterLoginSuccess() {
        loginCount++;
        lastLoginAt = now();
    }

    public String newApiToken(Jwt jwt, String[] authorities) {
        Jwt.Claims claims = Jwt.Claims.of(id, name, email, authorities);
        return jwt.newToken(claims);
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if(!passwordEncoder.matches(credentials, password)) {
            throw new IllegalArgumentException("Bad credential");
        }
    }

    public void addCandyHistory(CandyHistory candyHistory) {
        if (this.candyHistories == null) {
            this.candyHistories = new ArrayList<>();
        }
        candyHistories.add(candyHistory);
    }
}
