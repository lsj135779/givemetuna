package com.sparta.givemetuna.domain.card.dto.request;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequestDto {

    @Size(max = 500)
    @NotBlank(message = "Title은 필수입니다.")
    private String title;

    private String assignorAccount;

    private CardPriority cardPriority;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp closedAt;
}
