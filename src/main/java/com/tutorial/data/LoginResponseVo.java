package com.tutorial.data;

import java.time.LocalDate;
import com.tutorial.enums.AuthorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginResponseVo {
    private String accessToken;
    private String refreshToken;
    private String userName;
    private AuthorityEnum userAuthority;
    private LocalDate expiredAt;
}
