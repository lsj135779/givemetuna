package com.sparta.givemetuna.domain.card.dto.response;

import com.sparta.givemetuna.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCardAssigneeResponseDto {

    private String assigneeAccount;

    @Builder
    private UpdateCardAssigneeResponseDto(String assigneeAccount) {
        this.assigneeAccount = assigneeAccount;
    }

    public static UpdateCardAssigneeResponseDto of(User user) {

        return UpdateCardAssigneeResponseDto.builder()
                .assigneeAccount(user.getAccount())
                .build();
    }
}
