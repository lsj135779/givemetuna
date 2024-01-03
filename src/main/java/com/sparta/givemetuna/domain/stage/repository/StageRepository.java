package com.sparta.givemetuna.domain.stage.repository;

import com.sparta.givemetuna.domain.stage.entity.Stage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {

	List<Stage> findByBoardId(Long boardId);

	Optional<Stage> findByIdAndBoardId(long stageId, long boardId);
}
