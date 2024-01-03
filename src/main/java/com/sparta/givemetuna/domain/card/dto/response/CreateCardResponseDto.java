package com.sparta.givemetuna.domain.card.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardResponseDto {

	private Long boardId;

	private Long stageId;

	private String title;

	private String account;

	private CardPriority cardPriority;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private Timestamp startedAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private Timestamp closedAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private LocalDateTime createdAt;

	@Builder
	private CreateCardResponseDto(Long boardId, Long stageId, String title, String account,
		CardPriority cardPriority, Timestamp startedAt, Timestamp closedAt,
		LocalDateTime createdAt) {

		this.boardId = boardId;
		this.stageId = stageId;
		this.title = title;
		this.account = account;
		this.cardPriority = cardPriority;
		this.startedAt = startedAt;
		this.closedAt = closedAt;
		this.createdAt = createdAt;
	}

	public static CreateCardResponseDto of(Card card, Stage stage, User assignor) {

		return CreateCardResponseDto.builder()
			.boardId(stage.getBoard().getId())
			.stageId(card.getStage().getId())
			.title(card.getTitle())
			.account(assignor.getAccount())
			.cardPriority(card.getCardPriority())
			.startedAt(card.getStartedAt())
			.closedAt(card.getClosedAt())
			.createdAt(card.getCreatedAt())
			.build();
	}
}
