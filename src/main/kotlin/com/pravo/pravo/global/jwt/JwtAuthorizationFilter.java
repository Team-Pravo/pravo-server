package com.pravo.pravo.global.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {

    private final String[] whiteListUris = new String[]{"/login"}; //URLs do not need authorized
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (whiteListCheck(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        if (!isContainToken(httpServletRequest)) {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
            return;
        }

        try {
            String token = getToken(httpServletRequest);
            AuthenticateUser authenticateUser = getAuthenticateUser(token);
            httpServletRequest.setAttribute("authenticatedUser", authenticateUser);
            chain.doFilter(request, response);
        } catch (JsonParseException e) {
            log.error("JsonParseException" + e.getMessage());
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.error("JwtException" + e.getMessage());
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
        } catch (ExpiredJwtException e) {
            log.error("JwtTokenExpired" + e.getMessage());
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "토큰이 만료 되었습니다");
        }
    }

    private boolean whiteListCheck(String uri) {
        return PatternMatchUtils.simpleMatch(whiteListUris, uri);
    }

    private boolean isContainToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization.substring(7);
    }

    private AuthenticateUser getAuthenticateUser(String token) throws JsonProcessingException {
        String claimsSubject = jwtTokenProvider.extractSubject(token);
        return new AuthenticateUser(claimsSubject);
    }
}