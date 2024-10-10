package com.pravo.pravo.global.oauth.domain;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.oauth.domain.dto.MemberWithTokenDTO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OauthMemberClientComposite {

    private final Map<OauthSocialType, OauthMemberClient> mapping;

    public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
                .collect(toMap(
                        OauthMemberClient::socialType,
                        identity()
                ));
    }

    public MemberWithTokenDTO fetch(OauthSocialType oauthSocialType, String authCode) {
        return getClient(oauthSocialType).fetch(authCode);
    }

    private OauthMemberClient getClient(OauthSocialType oauthSocialType) {
        return Optional.ofNullable(mapping.get(oauthSocialType))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
    }
}
