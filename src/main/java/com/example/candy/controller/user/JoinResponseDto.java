package com.example.candy.controller.user;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
public class JoinResponseDto {
    private final String apiToken;

    private final UserDto user;

    public JoinResponseDto(String apiToken, UserDto user) {
        checkArgument(apiToken != null, "apiToken must be provided.");
        checkArgument(user != null, "user must be provided.");

        this.apiToken = apiToken;
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("apiToken", apiToken)
                .append("user", user)
                .toString();
    }
}
