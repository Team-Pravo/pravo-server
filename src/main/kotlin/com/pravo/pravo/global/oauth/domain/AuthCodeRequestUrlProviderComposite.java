package com.pravo.pravo.global.oauth.domain;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class AuthCodeRequestUrlProviderComposite {

    private final Map<OauthSocialType, AuthCodeRequestUrlProvider> mapping;

    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
        mapping = providers.stream()
                .collect(toMap(
                        AuthCodeRequestUrlProvider::socialType,
                        identity()
                ));
    }

    public String provide(OauthSocialType oauthSocialType) {
        return getProvider(oauthSocialType).provide();
    }

    private AuthCodeRequestUrlProvider getProvider(OauthSocialType oauthSocialType) {
        return Optional.ofNullable(mapping.get(oauthSocialType))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
    }
}
