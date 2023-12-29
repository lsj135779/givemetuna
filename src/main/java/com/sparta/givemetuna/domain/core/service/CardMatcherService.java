package com.sparta.givemetuna.domain.core.service;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.service.BoardService;
import com.sparta.givemetuna.domain.card.dto.request.CreateCardRequestDto;
import com.sparta.givemetuna.domain.card.dto.response.CreateCardResponseDto;
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

    private final BoardService boardService;
    private final StageService stageService;
    private final UserService userService;
    private final CardService cardService;


    public CreateCardResponseDto createCard(Long boardId, Long stageId, User user,
            CreateCardRequestDto requestDto) {

        // 보드가 있는지
        Board board = boardService.getBoard(boardId);
        // 보드에 스테이지가 있는지 확인
        Stage stage = stageService.checkStage(board.getId(), stageId);
        // 보드에 생성자 유저 & 생성자 유저의 권한 확인 / 관리자 or 매니저
        User creator = userService.checkUser(board.getId(), user.getId());
        // requestDto account(매니저) 체킹
        User manager = userService.findbyAccount(requestDto.getAccount());
        // 보드에 매니저 유저 & 매니저 유저의 권한 확인 // 관리자 or 매니저
        User checkedUser = userService.checkUser(board.getId(), manager.getId());

        // 카드 생성
        return cardService.createCard(stage, checkedUser, requestDto);
    }
}
