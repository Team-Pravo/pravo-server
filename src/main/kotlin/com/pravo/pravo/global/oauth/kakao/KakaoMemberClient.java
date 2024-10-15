package com.pravo.pravo.global.oauth.kakao;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.oauth.domain.OauthMemberClient;
import com.pravo.pravo.global.oauth.domain.OauthSocialType;
import com.pravo.pravo.global.oauth.kakao.config.KakaoOauthConfig;
import com.pravo.pravo.global.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthSocialType socialType() {
        return OauthSocialType.KAKAO;
    }

    @Override
    public Member fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        log.info("Token request parameters: {}", params);

        return params;
    }
}
