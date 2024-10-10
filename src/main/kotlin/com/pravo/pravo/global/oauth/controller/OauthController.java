package com.pravo.pravo.global.oauth.controller;

import com.pravo.pravo.global.oauth.domain.OauthSocialType;
import com.pravo.pravo.global.oauth.domain.dto.MemberWithTokenDTO;
import com.pravo.pravo.global.oauth.domain.dto.ResponseLoginDTO;
import com.pravo.pravo.global.oauth.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/{oauthSocialType}")
    public ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable String oauthSocialType,
            HttpServletResponse response
    ) {
        OauthSocialType socialType = OauthSocialType.fromName(oauthSocialType);
        String redirectUrl = oauthService.getAuthCodeRequestUrl(socialType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{oauthSocialType}")
    public ResponseEntity<ResponseLoginDTO> login(
            @PathVariable String oauthSocialType,
            @RequestParam("code") String code
    ) {
        OauthSocialType socialType = OauthSocialType.fromName(oauthSocialType);
        MemberWithTokenDTO memberWithToken = oauthService.login(socialType, code);
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
        if (memberWithToken != null) {
            ResponseLoginDTO.UserLoginInfo userLoginInfo = new ResponseLoginDTO.UserLoginInfo(
                    memberWithToken.getMember().getId(),
                    memberWithToken.getMember().getOauthId().oauthSocialId(),
                    memberWithToken.getMember().getName(),
                    memberWithToken.getMember().getProfileImage());
            responseLoginDTO.setUserLoginInfo(userLoginInfo);
            responseLoginDTO.setAccessToken(memberWithToken.getAccessToken());
        }
        log.info("responseLoginDTO: "+responseLoginDTO);
        return ResponseEntity.ok().body(responseLoginDTO);
    }
}