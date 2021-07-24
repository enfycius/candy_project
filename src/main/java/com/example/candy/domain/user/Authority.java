package com.example.candy.domain.user;

public enum Authority {
    STUDENT("ROLE_STUDENT"),
    PARENT("ROLE_PARENT"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    Authority(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Authority of(String name) {
        for (Authority role : Authority.values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        return null;
    }


}

