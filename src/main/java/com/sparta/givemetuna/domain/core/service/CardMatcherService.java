package com.sparta.givemetuna.domain.core.service;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAccountRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardStageRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardTitleRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardTitleResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardMatcherService {

    private final StageService stageService;
    private final UserService userService;
    private final CardService cardService;


    public CreateCardResponseDto createCard(Long boardId, Long stageId, User user,
            CreateCardRequestDto requestDto) {

        // 보드에 스테이지가 있는지 확인
        Stage stage = stageService.checkStage(boardId, stageId);
        // 보드에 생성자 유저 & 생성자 유저의 권한 확인 / 관리자 or 매니저
        User creator = userService.checkUser(boardId, user.getId());
        // 관리자 or 매니저 체킹
        checkBoardAuthority(creator);

        // requestDto account(매니저) 체킹 // requestDto account 가 null 일 수 있음
        User assignor;
        // requestDto account 가 null 이면
        if (requestDto.getAssignorAccount() == null && requestDto.getAssignorAccount().isEmpty()) {

            return cardService.createCard(stage, creator, null, requestDto);
        }
        // requestDto account 가 null 이 아닌 경우 유저 찾기
        User checkedUser = userService.findbyAccount(requestDto.getAssignorAccount());

        // 보드에 매니저 유저 & 매니저 유저의 권한 확인
        assignor = userService.checkUser(boardId, checkedUser.getId());
        // 관리자 or 매니저 체킹
        checkBoardAuthority(assignor);
        // 카드 생성
        return cardService.createCard(stage, creator, assignor, requestDto);
    }

    public UpdateCardStageResponseDto updateCardStage(Long boardId, Long stageId, Long cardId,
            User user, UpdateCardStageRequestDto requestDto) {
        // 보드에 스테이지가 있는지 확인
        Stage beforeStage = stageService.checkStage(boardId, stageId);
        // 옮기려는 스테이지가 있는지 확인
        Stage afterStage = stageService.checkStage(boardId, requestDto.getStageId());
        // 스테이지에 카드 있는지 확인
        Card card = cardService.checkStageCard(beforeStage.getId(), cardId);
        // 유저가 권한이 있는지 확인
        cardService.checkAssignor(card.getId(), user);
        // 스테이지 옮기기
        return cardService.updateStage(afterStage, card);
    }

    public UpdateCardTitleResponseDto updateCardTitle(Long boardId, Long stageId, Long cardId,
            User user, UpdateCardTitleRequestDto requestDto) {

        Stage stage = stageService.checkStage(boardId, stageId);
        Card card = cardService.checkStageCard(stage.getId(), cardId);
        cardService.checkAssignor(card.getId(), user);
        return cardService.updateTitle(requestDto.getTitle(), card);
    }

    public UpdateCardAccountResponseDto updateCardAccount(Long boardId, Long stageId, Long cardId,
            User user, UpdateCardAccountRequestDto requestDto) {

        Stage stage = stageService.checkStage(boardId, stageId);
        Card card = cardService.checkStageCard(stage.getId(), cardId);
        cardService.checkAssignor(card.getId(), user);
        User checkedUser = userService.findbyAccount(requestDto.getAssignorAccount());
        User assignor = userService.checkUser(boardId, checkedUser);
        checkBoardAuthority(assignor);
        return cardService.updateAccount(assignor, card);
    }

    private void checkBoardAuthority(User user) {
        if (user.getAccount().equals("일반유저")) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
