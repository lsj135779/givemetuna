package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardPeriodRequestDto {

	@NotNull(message = "yyyy-MM-dd HH:mm:ss 형태로 입력해주세요.")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startedAt;

	@NotNull(message = "yyyy-MM-dd HH:mm:ss 형태로 입력해주세요.")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp closedAt;
}
