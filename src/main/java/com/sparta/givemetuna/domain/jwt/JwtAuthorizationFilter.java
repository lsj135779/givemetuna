package com.sparta.givemetuna.domain.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ObjectMapper objectMapper;

        public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsServiceImpl, ObjectMapper objectMapper){
            this.jwtUtil =  jwtUtil;
            this.userDetailsServiceImpl = userDetailsServiceImpl;
            this.objectMapper = objectMapper;
        }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtil.resolveToken(httpServletRequest);

        if(Objects.nonNull(token)) {
            if(jwtUtil.validateToken(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);

                // 인증정보에 유저정보(account) 넣기
                // username -> user 조회
                String account = info.getSubject();
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                // -> userDetails 에 담고
                UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.getUserDetails(account);
                // -> authentication의 principal 에 담고
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
                // -> securityContent 에 담고
                context.setAuthentication(authentication);
                // -> SecurityContextHolder 에 담고
                SecurityContextHolder.setContext(context);
                // -> 이제 @AuthenticationPrincipal 로 조회할 수 있음
            } else {
                // 인증정보가 존재하지 않을때
                CommonResponseDTO commonResponseDto = new CommonResponseDTO("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
