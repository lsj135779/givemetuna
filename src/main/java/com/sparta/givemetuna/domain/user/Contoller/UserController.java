package com.sparta.givemetuna.domain.user.Contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.jwt.JwtUtil;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.exception.*;
import com.sparta.givemetuna.domain.user.service.UserInfoService;
import com.sparta.givemetuna.domain.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RequestMapping("/api")
@RestController
public class UserController {
    private final UserService userService;
    private final UserInfoService userInfoService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, UserInfoService userInfoService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userInfoService =  userInfoService;
        this.jwtUtil = jwtUtil;
    }

    //회원가입
    @PostMapping("/users/signup")
    public ResponseEntity<CommonResponseDTO> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){

        userService.signup(signUpRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDTO("회원가입 성공", HttpStatus.CREATED.value()));
    }

    //로그인
    @PostMapping("/users/login")
    public ResponseEntity<CommonResponseDTO> login(@RequestBody SignUpRequestDTO signUpRequestDTO, HttpServletResponse httpServletResponse) throws LoginInvalidAccountException, LoginInvalidPasswordException {
        userService.login(signUpRequestDTO);
        httpServletResponse.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(signUpRequestDTO.getAccount()));
        return ResponseEntity.ok().body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
    }

    //사용자 정보 조회
    @GetMapping("/users/{account}")
    public ResponseEntity<UserInfoResponseDTO> getUserProfile(@PathVariable String account) {
            UserInfoResponseDTO userInfoResponseDTO = userInfoService.getUserInfo(account);
            return ResponseEntity.ok().body(userInfoResponseDTO);
    }

    //사용자 정보 수정
    @PatchMapping ("/users/{account}")
    public ResponseEntity<UserInfoResponseDTO> updateUserProfile(@PathVariable String account, @RequestBody UserInfoRequestDTO userInfoRequestDTO,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        UserInfoResponseDTO userInfoResponseDTO = userInfoService.updateUser(account, userInfoRequestDTO, userDetails.getUser());
        return ResponseEntity.ok().body(userInfoResponseDTO);
    }

    //사용자 삭제
    @DeleteMapping("/users/{account}")
    public ResponseEntity<CommonResponseDTO> deleteUser(@PathVariable String account, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userInfoService.deleteUser(account, userDetails.getUser());
        return ResponseEntity.ok().body(new CommonResponseDTO("유저 삭제 성공", HttpStatus.OK.value()));
    }
}
