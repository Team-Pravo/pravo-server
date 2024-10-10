package com.pravo.pravo.global.oauth.domain;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.oauth.domain.dto.MemberWithTokenDTO;

public interface OauthMemberClient {

    OauthSocialType socialType();

    MemberWithTokenDTO fetch(String code);
}
