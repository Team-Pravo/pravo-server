package com.pravo.pravo.domain.member.model;

import com.pravo.pravo.global.oauth.domain.OauthId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;

    @Embedded
    private OauthId oauthId;

//    private String refreshToken;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
    }
}
