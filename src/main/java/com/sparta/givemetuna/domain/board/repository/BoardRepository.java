package com.sparta.givemetuna.domain.board.repository;

import com.sparta.givemetuna.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
