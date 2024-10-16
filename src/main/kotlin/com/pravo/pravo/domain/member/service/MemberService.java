package com.pravo.pravo.domain.member.service;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> fetchAllUser() {
        return this.memberRepository.findAll();
    }


    public Member handleGetMemberByEmail(String email) {
        return this.memberRepository.findByEmail(email);
    }
}
