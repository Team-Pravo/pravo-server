package com.pravo.pravo.global.oauth.domain;

public interface AuthCodeRequestUrlProvider {

    OauthSocialType socialType();

    String provide();
}
