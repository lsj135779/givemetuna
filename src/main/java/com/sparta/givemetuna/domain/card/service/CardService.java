package com.sparta.givemetuna.domain.card.service;

import static com.sparta.givemetuna.domain.card.constant.CardConstant.DEFAULT_ISDONE;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.checklist.service.ChecklistService;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;
    private final ChecklistService checklistService;

    @Transactional
    public CreateCardResponseDto createCard(Stage stage, User creator, User assignor,
            CreateCardRequestDto requestDto) {

        Card saveCard = Card.builder()
                .creator(creator)
                .assignor(assignor)
                .stage(stage)
                .title(requestDto.getTitle())
                .priority(requestDto.getPriority())
                .isDone(DEFAULT_ISDONE)
                .startedAt(requestDto.getStartedAt())
                .closedAt(requestDto.getClosedAt())
                .build();

        cardRepository.save(saveCard);

        return CreateCardResponseDto.of(saveCard, stage, assignor);
    }

    public UpdateCardStageResponseDto updateStage(Stage afterStage, Card card) {
        card.updateStage(afterStage);
        return UpdateCardStageResponseDto.of(card);
    }

    public UpdateCardTitleResponseDto updateTitle(String title, Card card) {
        card.updateTitle(title);
        return UpdateCardTitleResponseDto.of(card);
    }

    public UpdateCardAccountResponseDto updateAccount(User assignor, Card card) {
        card.updateAssignorAccount(assignor);
        return UpdateCardAccountResponseDto.of(card);
    }
    private Card checkCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(
                ()-> new NullPointerException("없는 카드입니다."));
    }

    public Card checkStageCard(Long stageId, Long cardId) {
        Card card = checkCard(cardId);
        if (!card.getStage().getId().equals(stageId)) {
            throw new IllegalArgumentException("해당 카드는 다른 스테이지에 있습니다.");
        }
        return card;
    }


    public void checkAssignor(Long cardId, User user) {
        Card card = checkCard(cardId);
        if (!card.getAssignor().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 권한이 없습니다");
        }
    }

}
