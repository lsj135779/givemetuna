package com.sparta.givemetuna.domain.core.service;

import static com.sparta.givemetuna.domain.user.entity.Role.GENERAL_MANAGER;

import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAllAssignRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAssigneeRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardAssignorRequestDto;
import com.sparta.givemetuna.domain.card.dto.request.UpdateCardStageRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAllAssignResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAssigneeResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardAssignorResponseDto;
import com.sparta.givemetuna.domain.card.dto.response.UpdateCardStageResponseDto;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.exception.CardAssignorInvalidAuthorizationException;
import com.sparta.givemetuna.domain.card.exception.CardInvalidAuthorizationException;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.UserInfoService;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardMatcherService {

	private final BoardUserRoleValidator boardUserRoleValidator;

	private final StageService stageService;

	private final UserInfoService userInfoService;

	private final CardService cardService;


	/**
	 * 카드 assignor 와 로그인 사용자이거나 총관리자인 경우 정상처리 아닌 경우, 예외처리
	 *
	 * @param userDetails 유저 인증정보
	 * @param boardId     보드 아이디
	 * @param card        카드
	 */
	private void checkCardAssignor(UserDetailsImpl userDetails, Long boardId, Card card) {
		User user = userInfoService.getUser(userDetails.getUser().getAccount());// 영속성 컨텍스트 X;
		User assignor = card.getAssignor(); // Card -> User 영속성 컨텍스트 O
		boolean isGmInBoard = user.getBoardUserRoles().stream()
			.filter(boardUserRole -> boardUserRole.getRole().equals(GENERAL_MANAGER))
			.anyMatch(boardUserRole -> boardUserRole.getBoard().getId().equals(boardId));
		if (assignor.equals(user) || isGmInBoard) {
			return;
		}
		throw new CardAssignorInvalidAuthorizationException(user.getAccount(), assignor.getAccount());
	}

	public CreateCardResponseDto createCard(Long boardId, Stage stage, User client,
		CreateCardRequestDto requestDto) throws CardInvalidAuthorizationException {

		if (requestDto.getAssignorAccount() == null || requestDto.getAssignorAccount().isEmpty()) {

			return cardService.createCard(stage, client, client, requestDto);
		}
		User checkedUser = userInfoService.getUser(requestDto.getAssignorAccount());
		boardUserRoleValidator.validateRole(CardMatcherService.class, checkedUser.getId(), boardId);

		return cardService.createCard(stage, client, checkedUser, requestDto);
	}


	public UpdateCardStageResponseDto updateCardStage(Long boardId, Card card,
		UpdateCardStageRequestDto requestDto) {

		Stage afterStage = stageService.checkStage(boardId, requestDto.getStageId());

		return cardService.updateStage(afterStage, card);
	}

	public UpdateCardAllAssignResponseDto updateCardAllAssign(Long boardId, Card card,
		UpdateCardAllAssignRequestDto requestDto) {

		User nextAssignor = userInfoService.getUser(requestDto.getAssignorAccount());
		User assignee = userInfoService.getUser(requestDto.getAssigneeAccount());

		boardUserRoleValidator.validateRole(CardMatcherService.class, nextAssignor.getId(), boardId);
		Role role = boardUserRoleValidator.getRole(boardId, assignee.getId());

		return cardService.updateAllAssign(card, nextAssignor, assignee);
	}

	public UpdateCardAssignorResponseDto updateCardAssignor(Long boardId, Card card,
		UpdateCardAssignorRequestDto requestDto) {

		User assignor = userInfoService.getUser(requestDto.getAssignorAccount());
		boardUserRoleValidator.validateRole(CardMatcherService.class, assignor.getId(), boardId);

		return cardService.updateAssignor(card, assignor);
	}

	public UpdateCardAssigneeResponseDto updateCardAssignee(Long boardId, Card card,
		UpdateCardAssigneeRequestDto requestDto) {

		User assignee = userInfoService.getUser(requestDto.getAssigneeAccount());
		Role role = boardUserRoleValidator.getRole(boardId, assignee.getId());

		return cardService.updateAssignee(card, assignee);
	}
}
