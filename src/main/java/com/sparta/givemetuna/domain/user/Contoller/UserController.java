package com.sparta.givemetuna.domain.user.Contoller;

import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.jwt.JwtUtil;
import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
import com.sparta.givemetuna.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){

        try {
            userService.signup(signUpRequestDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.CONFLICT.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDTO("회원가입 성공", HttpStatus.CREATED.value()));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO> login(@RequestBody SignUpRequestDTO signUpRequestDTO, HttpServletResponse httpServletResponse) {
        try {
            userService.login(signUpRequestDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        httpServletResponse.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(signUpRequestDTO.getAccount()));

        return ResponseEntity.ok().body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
    }
}
