package com.pravo.pravo.domain.member.controller;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    // @CrossOrigin
    public String getHomePage() {
        return "hello";
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.fetchAllUser());
    }
}
