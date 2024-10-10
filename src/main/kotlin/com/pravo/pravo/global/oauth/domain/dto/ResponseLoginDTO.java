package com.pravo.pravo.global.oauth.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pravo.pravo.global.auth.dto.ResAccessTokenLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoginDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user_login_info")
    private UserLoginInfo userLoginInfo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginInfo{
        private long id;
        private String socialId;
        private String name;
        private String profileImage;

    }
}
