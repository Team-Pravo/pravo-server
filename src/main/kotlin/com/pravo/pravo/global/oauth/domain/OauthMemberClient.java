package com.pravo.pravo.global.oauth.domain;

import com.pravo.pravo.domain.member.model.Member;

public interface OauthMemberClient {

    OauthSocialType socialType();

    Member fetch(String code);
}
