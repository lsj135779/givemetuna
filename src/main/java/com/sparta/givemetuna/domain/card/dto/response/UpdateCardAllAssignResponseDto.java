package com.sparta.givemetuna.domain.card.dto.response;

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
public class UpdateCardAllAssignResponseDto {


    private Long boardId;
    private Long stageId;
    private String title;
    private String assignor;
    private CardPriority cardPriority;
    private Timestamp startedAt;
    private Timestamp closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private UpdateCardAllAssignResponseDto(Long boardId, Long stageId, String title,
            String assignor, CardPriority cardPriority, Timestamp startedAt,
            Timestamp closedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.boardId = boardId;
        this.stageId = stageId;
        this.title = title;
        this.assignor = assignor;
        this.cardPriority = cardPriority;
        this.startedAt = startedAt;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UpdateCardAllAssignResponseDto of(Card card) {

        return UpdateCardAllAssignResponseDto.builder()
                .boardId(card.getStage().getBoard().getId())
                .stageId(card.getStage().getId())
                .title(card.getTitle())
                .assignor(card.getAssignor().getAccount())
                .cardPriority(card.getCardPriority())
                .startedAt(card.getStartedAt())
                .closedAt(card.getClosedAt())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();
    }
}
