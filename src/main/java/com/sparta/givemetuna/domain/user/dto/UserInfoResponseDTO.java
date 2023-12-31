package com.sparta.givemetuna.domain.user.dto;

import com.sparta.givemetuna.domain.CommonResponseDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserInfoResponseDTO {
    private Long id;
    private String account;
    private String password;
    private String nickname;
    private String email;
    private String github;
    private String description;

    public UserInfoResponseDTO(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.github = user.getGithub();
        this.description = user.getDescription();
    }
}