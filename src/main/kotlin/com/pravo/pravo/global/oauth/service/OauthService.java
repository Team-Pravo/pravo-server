package com.pravo.pravo.global.oauth.service;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.jwt.JwtTokensGenerator;
import com.pravo.pravo.global.oauth.domain.AuthCodeRequestUrlProviderComposite;
import com.pravo.pravo.global.oauth.domain.OauthMemberClientComposite;
import com.pravo.pravo.global.oauth.domain.OauthSocialType;
import com.pravo.pravo.global.oauth.domain.dto.ResponseLoginDTO;
import com.pravo.pravo.global.oauth.repository.OauthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JwtTokensGenerator jwtTokensGenerator;

    public String getAuthCodeRequestUrl(OauthSocialType oauthSocialType) {
        return authCodeRequestUrlProviderComposite.provide(oauthSocialType);
    }

    public ResponseLoginDTO login(OauthSocialType oauthSocialType, String authCode) {
        Member oauthMember = oauthMemberClientComposite.fetch(oauthSocialType, authCode);
        Member loginMember = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
        ResponseLoginDTO.UserLoginInfo userLoginInfo = new ResponseLoginDTO.UserLoginInfo(
                loginMember.getId(),
                loginMember.getName(),
                loginMember.getProfileImage());
        responseLoginDTO.setUserLoginInfo(userLoginInfo);
        responseLoginDTO.setJwtTokens(jwtTokensGenerator.generate(loginMember.getId()));

        return responseLoginDTO;
    }
}
