package com.sparta.givemetuna.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "아이디는 소문자, 숫자 6~12자리입니다.")
    private String account;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "비밀번호는 소문자, 숫자 6~12자리입니다.")
    private String password;

    @NotBlank
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank
    private String nickname;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String github;

    private String description;
}
