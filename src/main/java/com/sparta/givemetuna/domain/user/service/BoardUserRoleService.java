package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardUserRoleService {

    private final BoardUserRoleRepository boardUserRoleRepository;

    @Transactional
    public BoardUserRole checkBoardUser(Long boardId, Long userId) {
        return boardUserRoleRepository.findByBoardIdAndUserId(boardId, userId)
                .orElseThrow(() -> new NullPointerException("해당 보드에 유저가 없습니다."));
    }
}
