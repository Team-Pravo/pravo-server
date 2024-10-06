package com.pravo.pravo.global.auth.controller;

import com.pravo.pravo.domain.member.model.Member;
import com.pravo.pravo.domain.member.service.MemberService;
import com.pravo.pravo.global.auth.dto.LoginDTO;
import com.pravo.pravo.global.auth.dto.ResAccessTokenLoginDTO;
import com.pravo.pravo.global.security.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;

    private final MemberService memberService;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil, MemberService memberService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResAccessTokenLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        // Load username/password into Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        // user authentication
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // create token
        String access_token = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResAccessTokenLoginDTO resAccessTokenLoginDTO = new ResAccessTokenLoginDTO();
        Member currentMemberDB = this.memberService.handleGetMemberByEmail(loginDTO.getUsername());
        if (currentMemberDB != null) {
            ResAccessTokenLoginDTO.UserLoginInfo userLoginInfo = new ResAccessTokenLoginDTO.UserLoginInfo(
                    currentMemberDB.getId(),
                    currentMemberDB.getEmail(),
                    currentMemberDB.getName());
            resAccessTokenLoginDTO.setUserLoginInfo(userLoginInfo);
        }

        resAccessTokenLoginDTO.setAccessToken(access_token);

        return ResponseEntity.ok().body(resAccessTokenLoginDTO);
    }
}
