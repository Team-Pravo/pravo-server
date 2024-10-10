package com.pravo.pravo.global.oauth.domain.dto;

import com.pravo.pravo.domain.member.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberWithTokenDTO {
    private Member member;
    private String accessToken;


    public MemberWithTokenDTO(Member member, String accessToken) {
        this.member = member;
        this.accessToken = accessToken;
    }

    public Member getMember() {
        return member;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
