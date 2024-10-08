package com.pravo.pravo.domain.member.controller;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    // @CrossOrigin
    public String getHomePage() {
        return "hello";
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.fetchAllUser());
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> createNewMember(@RequestBody Member member) {
        String hashPassword = this.passwordEncoder.encode(member.getPassword());
        member.setPassword(hashPassword);
        Member newMember = this.memberService.handleCreateUser(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
