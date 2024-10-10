package com.pravo.pravo.global.oauth.domain;

import static java.util.Locale.ENGLISH;

public enum OauthSocialType {

    KAKAO,
    ;

    public static OauthSocialType fromName(String type) {
        return OauthSocialType.valueOf(type.toUpperCase(ENGLISH));
    }
}
