package com.sparta.givemetuna.domain.user.Contoller;

import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.jwt.JwtUtil;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.service.UserInfoService;
import com.sparta.givemetuna.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {

	private final UserService userService;

	private final UserInfoService userInfoService;

	private final JwtUtil jwtUtil;

	public UserController(UserService userService, UserInfoService userInfoService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.userInfoService = userInfoService;
		this.jwtUtil = jwtUtil;
	}

	//회원가입
	@PostMapping("/signup")
	public ResponseEntity<CommonResponseDTO> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {

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
	public ResponseEntity<CommonResponseDTO> login(@RequestBody SignUpRequestDTO signUpRequestDTO,
		HttpServletResponse httpServletResponse) {
		try {
			userService.login(signUpRequestDTO);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}

		httpServletResponse.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(signUpRequestDTO.getAccount()));

		return ResponseEntity.ok().body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
	}

	//사용자 정보 조회
	@GetMapping("/{account}")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<UserInfoResponseDTO> getUserProfile(@PathVariable String account) {
		UserInfoResponseDTO userInfoResponseDTO = userInfoService.getUserInfo(account);
		return ResponseEntity.ok().body(userInfoResponseDTO);
	}

	//사용자 정보 수정
	@PatchMapping("/{account}")
	@SecurityRequirement(name = "Bearer Authentication")
	public ResponseEntity<UserInfoResponseDTO> updateUserProfile(@PathVariable String account,
		@RequestBody UserInfoRequestDTO userInfoRequestDTO,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		UserInfoResponseDTO userInfoResponseDTO = userInfoService.updateUser(account, userInfoRequestDTO, userDetails.getUser());
		return ResponseEntity.ok().body(userInfoResponseDTO);
	}
}
