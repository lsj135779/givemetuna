package com.sparta.givemetuna.domain.card.service;

import static com.sparta.givemetuna.domain.card.constant.CardConstant.DEFAULT_ISDONE;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
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
    public CreateCardResponseDto createCard(Stage stage, User checkedUser,
            CreateCardRequestDto requestDto) {

        Card saveCard = Card.builder()
                .creator(checkedUser.getId())
                .assignor(checkedUser.getId())
                .stage(stage)
                .title(requestDto.getTitle())
                .priority(requestDto.getPriority())
                .isDone(DEFAULT_ISDONE)
                .startedAt(requestDto.getStartedAt())
                .closedAt(requestDto.getClosedAt())
                .build();

        cardRepository.save(saveCard);

        return CreateCardResponseDto.of(saveCard, stage, checkedUser);
    }
}
