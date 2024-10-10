package com.pravo.pravo.global.oauth.kakao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "oauth.kakao")
@Component
public record KakaoOauthConfig(
        @Value("${oauth.kakao.client_id}")
        String clientId,

        @Value("${oauth.kakao.redirect_uri}")
        String redirectUri
) {
}