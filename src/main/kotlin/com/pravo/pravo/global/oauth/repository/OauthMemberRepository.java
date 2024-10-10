package com.pravo.pravo.global.oauth.repository;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.global.oauth.domain.OauthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthId(OauthId oauthId);
}