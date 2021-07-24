package com.example.candy.domain.user;


import com.example.candy.security.Jwt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
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

    public void afterLoginSuccess() {
        loginCount++;
        lastLoginAt = now();
    }

    public String newApiToken(Jwt jwt, String[] authorities) {
        Jwt.Claims claims = Jwt.Claims.of(id, name, email, authorities);
        return jwt.newToken(claims);
    }
}
