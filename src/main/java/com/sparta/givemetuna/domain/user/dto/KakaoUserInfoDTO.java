package com.sparta.givemetuna.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDTO {
    private Long id;
    private String nickname;
    private String email;

    public KakaoUserInfoDTO(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}