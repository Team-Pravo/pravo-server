package com.pravo.pravo.global.oauth.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pravo.pravo.global.jwt.JwtTokens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoginDTO {
    @JsonProperty("jwt_tokens")
    private JwtTokens jwtTokens;

    @JsonProperty("user_login_info")
    private UserLoginInfo userLoginInfo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginInfo{
        private long memberId;
        private String name;
        private String profileImage;

    }
}
