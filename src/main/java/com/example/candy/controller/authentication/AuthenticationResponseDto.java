package com.example.candy.controller.authentication;

import com.example.candy.controller.user.UserDto;
import com.example.candy.security.AuthenticationResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter @Setter
public class AuthenticationResponseDto {
    private String apiToken;

    private UserDto user;

    public AuthenticationResponseDto(AuthenticationResult source) {
        copyProperties(source, this);

        this.user = new UserDto(source.getUser());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("apiToken", apiToken)
                .append("user", user)
                .toString();
    }
}
