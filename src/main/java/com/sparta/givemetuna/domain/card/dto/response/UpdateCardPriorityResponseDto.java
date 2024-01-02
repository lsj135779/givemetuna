package com.sparta.givemetuna.domain.card.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCardPriorityResponseDto {

	private Long boardId;

	private Long stageId;

	private String title;

	private String assignorAccount;

	private CardPriority cardPriority;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private Timestamp startedAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private Timestamp closedAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private LocalDateTime createdAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private LocalDateTime updatedAt;

	@Builder
	private UpdateCardPriorityResponseDto(Long boardId, Long stageId, String title,
		String assignorAccount, CardPriority cardPriority, Timestamp startedAt,
		Timestamp closedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {

		this.boardId = boardId;
		this.stageId = stageId;
		this.title = title;
		this.assignorAccount = assignorAccount;
		this.cardPriority = cardPriority;
		this.startedAt = startedAt;
		this.closedAt = closedAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static UpdateCardPriorityResponseDto of(Card card) {

		return UpdateCardPriorityResponseDto.builder()
			.boardId(card.getStage().getBoard().getId())
			.stageId(card.getStage().getId())
			.title(card.getTitle())
			.assignorAccount(card.getAssignor().getAccount())
			.cardPriority(card.getCardPriority())
			.startedAt(card.getStartedAt())
			.closedAt(card.getClosedAt())
			.createdAt(card.getCreatedAt())
			.updatedAt(card.getUpdatedAt())
			.build();
	}
}
