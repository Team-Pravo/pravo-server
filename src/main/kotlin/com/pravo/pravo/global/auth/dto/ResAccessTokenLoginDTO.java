package com.pravo.pravo.global.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResAccessTokenLoginDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user_login_info")
    private UserLoginInfo userLoginInfo;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }

    public static class UserLoginInfo {

        private long id;
        private String email;
        private String name;

        public UserLoginInfo(long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public UserLoginInfo() {
        }

        public long getId() {
            return this.id;
        }

        public String getEmail() {
            return this.email;
        }

        public String getName() {
            return this.name;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}