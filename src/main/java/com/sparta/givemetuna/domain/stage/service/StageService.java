package com.sparta.givemetuna.domain.stage.service;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.stage.dto.CreateStageRequestDto;
import com.sparta.givemetuna.domain.stage.dto.CreateStageResponseDto;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageRequestDto;
import com.sparta.givemetuna.domain.stage.dto.UpdateStageResponseDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.exception.SelectStageNotFoundException;
import com.sparta.givemetuna.domain.stage.repository.StageRepository;
import com.sparta.givemetuna.domain.user.entity.User;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StageService {

	private final StageRepository stageRepository;

	private final BoardRepository boardRepository;

	// stage 생성
	public CreateStageResponseDto createStage(Long board_id, CreateStageRequestDto requestDto, User user) {

		Board board = boardRepository.findById(board_id)
			.orElseThrow(() -> new IllegalArgumentException("The board does not exist"));

		Stage stage = new Stage(board, requestDto.getCategory(), user);
		stageRepository.save(stage);

		return new CreateStageResponseDto(stage);
	}

	// stage 수정
	@Transactional
	public UpdateStageResponseDto updateStage(Long stage_id, UpdateStageRequestDto requestDto, User user) {

		Stage stage = getStageById(stage_id);

		checkStageAuth(stage, user);

		stage.setCategory(requestDto);

		return new UpdateStageResponseDto(stage);
	}

	// stage 삭제
	public void deleteStage(Long stage_Id, User user) {
		Stage stage = getStageById(stage_Id);
		checkStageAuth(stage, user);
		stageRepository.delete(stage);
	}

	public Stage getStageById(Long id) {
		return stageRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("The stage does not exist"));
	}

	//작성자만 수정가능한 stage
	public void checkStageAuth(Stage stage, User user) {
		if (!user.getId().equals(stage.getUser().getId())) {
			throw new RejectedExecutionException("총 책임자만 접근할 수 있습니다.");
		}
	}


	public Stage checkStage(Long boardId, Long stageId) {
		return stageRepository.findByIdAndBoardId(stageId, boardId)
			.orElseThrow(SelectStageNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public Stage getStage(Long stageId) {
		return getStageById(stageId);
	}
}
