package com.sparta.givemetuna.domain.user.Contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.jwt.JwtUtil;
import com.sparta.givemetuna.domain.user.service.KakaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class KakaoController {
    private final KakaoService kakaoService;

    public KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @GetMapping("/users/kakao/callback")
    public ResponseEntity<CommonResponseDTO> kakaoLogin(@RequestParam(name = "code") String code, HttpServletResponse response) throws JsonProcessingException {

        String token = kakaoService.kakaoLogin(code);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().body(new CommonResponseDTO("카카오 로그인 성공", HttpStatus.OK.value()));
    }
}