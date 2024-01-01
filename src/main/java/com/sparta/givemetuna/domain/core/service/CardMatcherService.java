package com.sparta.givemetuna.domain.core.service;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAccountRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardStageRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAccountResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
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


	public CreateCardResponseDto createCard(Long boardId, Stage stage, User client,
		CreateCardRequestDto requestDto) {

		// requestDto account(매니저) 체킹 // requestDto account 가 null 일 수 있음
		// requestDto account 가 null 이면
		if (requestDto.getAssignorAccount() == null || requestDto.getAssignorAccount().isEmpty()) {

			return cardService.createCard(stage, client, client, requestDto);
		}
		// requestDto account 가 null 이 아닌 경우 유저 찾기
		User checkedUser = userService.findbyAccount(requestDto.getAssignorAccount());
		// 보드에 담당자 유저 존재 확인
		User assignor = userService.checkUser(boardId, checkedUser);
		// 관리자 or 매니저 체킹
		checkBoardAuthority(assignor);
		// 카드 생성
		return cardService.createCard(stage, client, assignor, requestDto);
	}

	public UpdateCardStageResponseDto updateCardStage(Long boardId, Card card,
		UpdateCardStageRequestDto requestDto) {

		// 옮기려는 스테이지가 있는지 확인
		Stage afterStage = stageService.checkStage(boardId, requestDto.getStageId());
		// 스테이지 옮기기
		return cardService.updateStage(afterStage, card);
	}

	public UpdateCardAccountResponseDto updateCardAccount(Long boardId, Card card,
		UpdateCardAccountRequestDto requestDto) {

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
