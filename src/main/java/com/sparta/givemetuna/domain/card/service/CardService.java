package com.sparta.givemetuna.domain.card.service;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.SelectCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPeriodResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPriorityResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.checklist.service.ChecklistService;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        if (requestDto.getCardPriority() == null) {

            Card saveCard = Card.builder().creator(creator).assignor(assignor).stage(stage)
                    .title(requestDto.getTitle()).cardPriority(CardPriority.NON)
                    .startedAt(requestDto.getStartedAt()).closedAt(requestDto.getClosedAt())
                    .build();

            cardRepository.save(saveCard);

            return CreateCardResponseDto.of(saveCard, stage, assignor);
        }

        Card saveCard = Card.builder().creator(creator).assignor(assignor).stage(stage)
                .title(requestDto.getTitle()).cardPriority(requestDto.getCardPriority())
                .startedAt(requestDto.getStartedAt()).closedAt(requestDto.getClosedAt()).build();

        cardRepository.save(saveCard);

        return CreateCardResponseDto.of(saveCard, stage, assignor);
    }

    @Transactional
    public UpdateCardStageResponseDto updateStage(Stage afterStage, Card card) {

        card.updateStage(afterStage);

        return UpdateCardStageResponseDto.of(card);
    }

    @Transactional
    public UpdateCardTitleResponseDto updateTitle(Card card, String title) {

        card.updateTitle(title);

        return UpdateCardTitleResponseDto.of(card);
    }

    @Transactional
    public UpdateCardAccountResponseDto updateAccount(User assignor, Card card) {

        card.updateAssignorAccount(assignor);

        return UpdateCardAccountResponseDto.of(card);
    }

    public UpdateCardPriorityResponseDto updatePriority(Card card, CardPriority cardPriority) {

        card.updatePriority(cardPriority);

        return UpdateCardPriorityResponseDto.of(card);
    }

    public UpdateCardPeriodResponseDto updatePeriod(Card card, Timestamp startedAt,
            Timestamp closedAt) {

        card.updatePeriod(startedAt, closedAt);

        return UpdateCardPeriodResponseDto.of(card);
    }

    public Page<SelectCardResponseDto> getCardPage(Stage stage, Pageable pageable) {

        Page<Card> allCardByStageId = cardRepository.findAllByStageId(stage.getId(), pageable);
        List<SelectCardResponseDto> responseDtoList = new ArrayList<>();

        for (Card card : allCardByStageId) {
            SelectCardResponseDto responseDto = SelectCardResponseDto.of(card);
            responseDtoList.add(responseDto);
        }

        return new PageImpl<> (responseDtoList, pageable, allCardByStageId.getTotalElements());
    }

    public void delete(Card card) {
        cardRepository.delete(card);
    }

    private Card checkCard(Long cardId) {

        return cardRepository.findById(cardId)
                .orElseThrow(() -> new NullPointerException("없는 카드입니다."));
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
