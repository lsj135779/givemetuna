package com.sparta.givemetuna.domain.card.dto.request;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardRequestDto {

    @Size(max = 500)
    @NotNull
    private String title;

    private String assignorAccount;

    private CardPriority cardPriority;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp closedAt;

    @Builder
    private CreateCardRequestDto(String title, String assignorAccount, CardPriority cardPriority,
            Timestamp startedAt, Timestamp closedAt) {
        this.title = title;
        this.assignorAccount = assignorAccount;
        this.cardPriority = cardPriority;
        this.startedAt = startedAt;
        this.closedAt = closedAt;
    }
}
