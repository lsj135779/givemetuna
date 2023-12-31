package com.sparta.givemetuna.domain.card.controller;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAccountRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardStageRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardTitleRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdatetCardPriorityRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPriorityResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.core.service.CardMatcherService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/stage/{stageId}/cards")
public class CardController {

    private final CardMatcherService cardMatcherService;
    private final StageService stageService;
    private final UserService userService;
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CreateCardResponseDto> createCard(@PathVariable Long boardId,
            @PathVariable Long stageId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody CreateCardRequestDto requestDto) {

        Stage stage = stageService.checkStage(boardId, stageId);
        User client = checkClientAuthority(boardId, userDetails.getUser());

        CreateCardResponseDto responseDto = cardMatcherService.createCard(boardId, stage,
                client, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{cardId}/phase")
    public ResponseEntity<UpdateCardStageResponseDto> updateCardStage(@PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardStageRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientAuthority(boardId, userDetails.getUser());

        UpdateCardStageResponseDto responseDto = cardMatcherService.updateCardStage(boardId,
                card, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{cardId}/title")
    public ResponseEntity<UpdateCardTitleResponseDto> updateCardTitle(@PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardTitleRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientAuthority(boardId, userDetails.getUser());

        UpdateCardTitleResponseDto responseDto = cardMatcherService.updateCardTitle(
                card, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{cardId}/account")
    public ResponseEntity<UpdateCardAccountResponseDto> updateCardAccount(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardAccountRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientAuthority(boardId, userDetails.getUser());

        UpdateCardAccountResponseDto responseDto = cardMatcherService.updateCardAccount(
                boardId, card, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{cardId}/priority")
    public ResponseEntity<UpdateCardPriorityResponseDto> updateCardPriority(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdatetCardPriorityRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientAuthority(boardId, userDetails.getUser());

        UpdateCardPriorityResponseDto responseDto = cardMatcherService.updateCardPriority(
                card, client, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    private Card checkAPI(Long boardId, Long stageId, Long cardId) {

        Stage stage = stageService.checkStage(boardId, stageId);

        return cardService.checkStageCard(stage.getId(), cardId);
    }

    private User checkClientAuthority(Long boardId, User user) {

        User client = userService.checkUser(boardId, user);

        if (client.getAccount().equals("일반유저")) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return client;
    }
}