package com.example.candy.security;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;

import static com.google.common.base.Preconditions.checkArgument;

public class JwtAuthentication {

    public final Long id;

    public final String name;

    public final String email;

    JwtAuthentication(Long id, String name, String email) {
        checkArgument(id != null, "id must be provided.");
        checkArgument(name != null, "name must be provided.");
        checkArgument(email != null, "email must be provided.");

        this.id =  id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("email", email)
                .toString();
    }

}
