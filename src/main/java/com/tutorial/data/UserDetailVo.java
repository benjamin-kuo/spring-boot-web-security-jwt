package com.tutorial.data;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import com.tutorial.enums.AuthorityEnum;
import com.tutorial.persistence.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserDetailVo implements UserDetails {
    private final UserEntity user;

    public UserDetailVo(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(AuthorityEnum.valueOf(user.getAuthority()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        log.info("isAccountNonExpired:{}",BooleanUtils.isFalse(user.getExpired()));
        return BooleanUtils.isFalse(user.getExpired());
    }

    @Override
    public boolean isAccountNonLocked() {
        log.info("isAccountNonLocked:{}",BooleanUtils.isFalse(user.getLocked()));
        return BooleanUtils.isFalse(user.getLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.info("isCredentialsNonExpired:{}",BooleanUtils.isFalse(user.getCredentialsExpired()));
        return BooleanUtils.isFalse(user.getCredentialsExpired());
    }

    @Override
    public boolean isEnabled() {
        log.info("isEnabled:{}", BooleanUtils.isTrue(user.getEnabled()));
        return BooleanUtils.isTrue(user.getEnabled());
    }

    public AuthorityEnum getUserAuthority() {
        return AuthorityEnum.valueOf(user.getAuthority());
    }

    public LocalDate getExpiredAt() {
        return user.getExpiredAt();
    }

}
