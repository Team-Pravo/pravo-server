package com.pravo.pravo.global.oauth.kakao;

import com.pravo.pravo.global.oauth.domain.AuthCodeRequestUrlProvider;
import com.pravo.pravo.global.oauth.domain.OauthSocialType;
import com.pravo.pravo.global.oauth.kakao.config.KakaoOauthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthSocialType socialType() {
        return OauthSocialType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthConfig.clientId())
                .queryParam("redirect_uri", kakaoOauthConfig.redirectUri())
                .toUriString();
    }
}
