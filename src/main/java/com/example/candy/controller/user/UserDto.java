package com.example.candy.controller.user;

import com.example.candy.domain.user.Authority;
import com.example.candy.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter @Setter
public class UserDto {

    private Long id;

    private String name;

    @Email
    private String email;

    private Authority authority;

    private String birth;

    private int loginCount;

    public UserDto(User source) {
        copyProperties(source, this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("authority", authority)
                .append("name", name)
                .append("email", email)
                .append("birth", birth)
                .append("loginCount", loginCount)
                .toString();
    }
}
