package com.pravo.pravo.global.oauth.service;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.oauth.domain.AuthCodeRequestUrlProviderComposite;

import com.pravo.pravo.global.oauth.domain.OauthMemberClientComposite;
import com.pravo.pravo.global.oauth.domain.OauthSocialType;
import com.pravo.pravo.global.oauth.domain.dto.MemberWithTokenDTO;
import com.pravo.pravo.global.oauth.repository.OauthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthSocialType oauthSocialType) {
        return authCodeRequestUrlProviderComposite.provide(oauthSocialType);
    }

    public MemberWithTokenDTO login(OauthSocialType oauthSocialType, String authCode) {
        MemberWithTokenDTO oauthMember = oauthMemberClientComposite.fetch(oauthSocialType, authCode);
        Member loginMember = oauthMemberRepository.findByOauthId(oauthMember.getMember().getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember.getMember()));
        oauthMember.getMember().setId(loginMember.getId());
        return oauthMember;
    }
}
