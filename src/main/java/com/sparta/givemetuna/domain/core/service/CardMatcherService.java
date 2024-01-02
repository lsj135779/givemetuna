package com.sparta.givemetuna.domain.core.service;

import static com.sparta.givemetuna.domain.user.entity.Role.WORKER;

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
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.BoardUserRoleService;
import com.sparta.givemetuna.domain.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardMatcherService {

    private final BoardUserRoleService boardUserRoleService;
    private final StageService stageService;
    private final UserInfoService userInfoService;
    private final CardService cardService;


    public CreateCardResponseDto createCard(Long boardId, Stage stage, User client,
            CreateCardRequestDto requestDto) {

        if (requestDto.getAssignorAccount() == null || requestDto.getAssignorAccount().isEmpty()) {

            return cardService.createCard(stage, client, client, requestDto);
        }
        User checkedUser = userInfoService.getUser(requestDto.getAssignorAccount());
        User assignor = checkBoardUserRole(boardId, checkedUser);

        return cardService.createCard(stage, client, assignor, requestDto);
    }


    public UpdateCardStageResponseDto updateCardStage(Long boardId, Card card,
            UpdateCardStageRequestDto requestDto) {

        Stage afterStage = stageService.checkStage(boardId, requestDto.getStageId());

        return cardService.updateStage(afterStage, card);
    }

    public UpdateCardAllAssignResponseDto updateCardAllAssign(Long boardId, Card card,
            UpdateCardAllAssignRequestDto requestDto) {

        User checkAssignor = userInfoService.getUser(requestDto.getAssignor());
        User checkAssignee = userInfoService.getUser(requestDto.getAssignee());

        User nextAssignor = checkBoardUserRole(boardId, checkAssignor);
        User assignee = checkBoardUserRole(boardId, checkAssignee);

        return cardService.updateAllAssign(card, nextAssignor, assignee);

    }

    public UpdateCardAssignorResponseDto updateCardAssignor(Long boardId, Card card,
            UpdateCardAssignorRequestDto requestDto) {

        User checkedUser = userInfoService.getUser(requestDto.getAssignor());
        User assignor = checkBoardUserRole(boardId, checkedUser);

        return cardService.updateAssignor(card, assignor);
    }

    public UpdateCardAssigneeResponseDto updateCardAssignee(Card card,
            UpdateCardAssigneeRequestDto requestDto) {

        User assignee = userInfoService.getUser(requestDto.getAssignee());

        return cardService.updateAssignee(card, assignee);
    }

    private User checkBoardUserRole(Long boardId, User user) {

        BoardUserRole clientRole = boardUserRoleService.checkBoardUser(boardId,
                user.getId());

        if (clientRole.getRole().equals(WORKER)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return clientRole.getUser();
    }
}
