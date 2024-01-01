package com.sparta.givemetuna.domain.card.controller;

import static com.sparta.givemetuna.domain.user.entity.Role.WORKER;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAccountRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardPeriodRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardStageRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardTitleRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdatetCardPriorityRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.SelectCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPeriodResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardPriorityResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.core.service.CardMatcherService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.BoardUserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final BoardUserRoleService boardUserRoleService;
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CreateCardResponseDto> createCard(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody CreateCardRequestDto requestDto) {

        Stage stage = stageService.checkStage(boardId, stageId);
        User client = checkClientRole(boardId, userDetails.getUser());

        CreateCardResponseDto responseDto = cardMatcherService.createCard(boardId, stage,
                client, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{cardId}/phase")
    public ResponseEntity<UpdateCardStageResponseDto> updateCardStage(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardStageRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientRole(boardId, userDetails.getUser());

        UpdateCardStageResponseDto responseDto = cardMatcherService.updateCardStage(boardId,
                card, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{cardId}/title")
    public ResponseEntity<UpdateCardTitleResponseDto> updateCardTitle(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardTitleRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientRole(boardId, userDetails.getUser());

        UpdateCardTitleResponseDto responseDto = cardService.updateTitle(card,
                requestDto.getTitle());

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
        User client = checkClientRole(boardId, userDetails.getUser());

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
        User client = checkClientRole(boardId, userDetails.getUser());

        UpdateCardPriorityResponseDto responseDto = cardService.updatePriority(
                card, requestDto.getCardPriority());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{cardId}/period")
    public ResponseEntity<UpdateCardPeriodResponseDto> updateCardPeriod(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UpdateCardPeriodRequestDto requestDto) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientRole(boardId, userDetails.getUser());

        UpdateCardPeriodResponseDto responseDto = cardService.updatePeriod(
                card, requestDto.getStartedAt(), requestDto.getClosedAt());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<SelectCardResponseDto> getCard(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId) {

        Card card = checkAPI(boardId, stageId, cardId);

        SelectCardResponseDto responseDto = SelectCardResponseDto.of(card);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<SelectCardResponseDto>> getCardPage(
            @PageableDefault(size = 5, sort = "card_id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long boardId,
            @PathVariable Long stageId) {

        Stage stage = stageService.checkStage(boardId, stageId);

        Page<SelectCardResponseDto> responseDtoList = cardService.getCardPage(stage, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Long> deleteCard(
            @PathVariable Long boardId,
            @PathVariable Long stageId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Card card = checkAPI(boardId, stageId, cardId);
        User client = checkClientRole(boardId, userDetails.getUser());
        cardService.delete(card);

        return ResponseEntity.status(HttpStatus.OK).body(cardId);
    }


    private Card checkAPI(Long boardId, Long stageId, Long cardId) {

        Stage stage = stageService.checkStage(boardId, stageId);

        return cardService.checkStageCard(stage.getId(), cardId);
    }

    private User checkClientRole(Long boardId, User user) {

        BoardUserRole clientRole = boardUserRoleService.checkBoardUser(boardId,
                user.getId());

        if (clientRole.getRole().equals(WORKER)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return clientRole.getUser();
    }
}