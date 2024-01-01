package com.sparta.givemetuna.domain.checklist.temp.boardTemp;

import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardTempService {

	private final BoardUserRoleRepository boardUserRoleRepository;

	public String getRole(Long boardId, Long userId) {
		BoardUserRole boardUserRole = boardUserRoleRepository.findByBoardIdAndUserId(boardId, userId)
			.orElseThrow(() -> new IllegalArgumentException("보드에 권한이 없는 유저입니다."));
//		return boardUserRole.getRole();
		return "";
	}
}
