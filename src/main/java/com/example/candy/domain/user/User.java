package com.example.candy.domain.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import  javax.validation.constraints.Email;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;


    @Email
    private String email;

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

    public void afterLoginSuccess() {
        loginCount++;
        lastLoginAt = now();
    }
}
