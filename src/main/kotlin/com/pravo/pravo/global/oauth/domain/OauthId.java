package com.pravo.pravo.global.oauth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthId {

    @Column(name = "oauth_social_id")
    private String oauthSocialId;

    @Enumerated(STRING)
    @Column(name = "oauth_social_type")
    private OauthSocialType oauthSocialType;
}
