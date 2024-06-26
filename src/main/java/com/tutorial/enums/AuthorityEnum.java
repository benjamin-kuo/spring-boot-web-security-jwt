package com.tutorial.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityEnum implements GrantedAuthority {
    ADMIN, STAFF, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
