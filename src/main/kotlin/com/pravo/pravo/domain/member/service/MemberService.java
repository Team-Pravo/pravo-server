package com.pravo.pravo.domain.member.service;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> fetchAllUser() {
        return this.memberRepository.findAll();
    }

    public Member handleCreateUser(Member member) {
        return this.memberRepository.save(member);
    }

    public Member handleGetMemberByEmail(String email) {
        return this.memberRepository.findByEmail(email);
    }
}
