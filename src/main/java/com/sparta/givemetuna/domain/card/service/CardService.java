package com.sparta.givemetuna.domain.card.service;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.SelectCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAllAssignResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAssigneeResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAssignorResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPeriodResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPriorityResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.exception.SelectCardNotFoundException;
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
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final ChecklistService checklistService;


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


    public UpdateCardStageResponseDto updateStage(Stage afterStage, Card card) {

        card.updateStage(afterStage);

        return UpdateCardStageResponseDto.of(card);
    }


    public UpdateCardTitleResponseDto updateTitle(Card card, String title) {

        card.updateTitle(title);

        return UpdateCardTitleResponseDto.of(card);
    }


    public UpdateCardAllAssignResponseDto updateAllAssign(Card card, User nextAssignor,
            User assignee) {

        card.updateAssignor(nextAssignor);
        checklistService.firstCreateChecklist(card, assignee);

        return UpdateCardAllAssignResponseDto.of(card);
    }


    public UpdateCardAssignorResponseDto updateAssignor(Card card, User assignor) {

        card.updateAssignor(assignor);

        return UpdateCardAssignorResponseDto.of(card);
    }


    public UpdateCardAssigneeResponseDto updateAssignee(Card card, User assignee) {

        checklistService.firstCreateChecklist(card, assignee);

        return UpdateCardAssigneeResponseDto.of(assignee);
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

    @Transactional(readOnly = true)
    public Page<SelectCardResponseDto> getCardPage(Stage stage, Pageable pageable) {

        Page<Card> allCardByStageId = cardRepository.findAllByStageId(stage.getId(), pageable);
        List<SelectCardResponseDto> responseDtoList = new ArrayList<>();

        for (Card card : allCardByStageId) {
            SelectCardResponseDto responseDto = SelectCardResponseDto.of(card);
            responseDtoList.add(responseDto);
        }

        return new PageImpl<>(responseDtoList, pageable, allCardByStageId.getTotalElements());
    }


    public void delete(Card card) {
        cardRepository.delete(card);
    }

    @Transactional(readOnly = true)
    public Card checkCard(Long cardId) {

        return cardRepository.findById(cardId)
                .orElseThrow(SelectCardNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Card checkStageCard(Stage stage, Long cardId) {

        Card card = checkCard(cardId);

        if (!card.getStage().getId().equals(stage.getId())) {
            throw new SelectCardNotFoundException(card.getTitle(), stage.getCategory());
        }
        return card;
    }
}
