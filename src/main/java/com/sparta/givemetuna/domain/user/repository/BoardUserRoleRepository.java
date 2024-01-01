package com.sparta.givemetuna.domain.user.repository;

import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRoleRepository extends JpaRepository<BoardUserRole, Long> {

	Optional<BoardUserRole> findByBoardIdAndUserId(Long boardId, Long userId);
}
