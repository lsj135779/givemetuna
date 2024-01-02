package com.sparta.givemetuna.domain.stage.repository;

import com.sparta.givemetuna.domain.stage.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByBoardId(Long boardId);
}
