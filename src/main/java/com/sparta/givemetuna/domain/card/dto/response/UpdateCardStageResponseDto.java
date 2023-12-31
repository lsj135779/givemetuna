package com.sparta.givemetuna.domain.card.dto.response;

import com.sparta.givemetuna.domain.card.entity.Card;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCardStageResponseDto {

    private Long boardId;
    private Long stageId;
    private String title;
    private String assignorAccount;
    private Integer priority;
    private Boolean isDone;
    private Timestamp startedAt;
    private Timestamp closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private UpdateCardStageResponseDto(Long boardId, Long stageId, String title, String assignorAccount,
            Integer priority, Boolean isDone, Timestamp startedAt, Timestamp closedAt,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.boardId = boardId;
        this.stageId = stageId;
        this.title = title;
        this.assignorAccount = assignorAccount;
        this.priority = priority;
        this.isDone = isDone;
        this.startedAt = startedAt;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UpdateCardStageResponseDto of(Card card) {
        return UpdateCardStageResponseDto.builder()
                .boardId(card.getStage().getBoard().getId())
                .stageId(card.getStage().getId())
                .title(card.getTitle())
                .assignorAccount(card.getAssignor().getAccount())
                .priority(card.getPriority())
                .isDone(card.getIsDone())
                .startedAt(card.getStartedAt())
                .closedAt(card.getClosedAt())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();
    }
}
