//package com.sparta.givemetuna.domain.user.Contoller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.sparta.givemetuna.domain.CommonResponseDTO;
//import com.sparta.givemetuna.domain.jwt.JwtUtil;
//
//import com.sparta.givemetuna.domain.user.service.UserInfoService;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RequestMapping("/api")
//@RestController
//public class KakaoController {
//
//    private final UserInfoService userInfoService;
//
//
//    public KakaoController(UserInfoService userInfoService) {
//        this.userInfoService =  userInfoService;
//    }
//
//    //카카오 로그인
//    @GetMapping("/user/kakao/callback")
//    public ResponseEntity<CommonResponseDTO> kakaoLogin(@RequestParam String code, HttpServletResponse httpServletResponse) throws JsonProcessingException {
//
//        String token = userInfoService.kakaoLogin(code);
//
//        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
//        cookie.setPath("/");
//        httpServletResponse.addCookie(cookie);
//
//        return ResponseEntity.ok().body(new CommonResponseDTO("카카오 로그인 성공", HttpStatus.OK.value()));
//    }
//}
