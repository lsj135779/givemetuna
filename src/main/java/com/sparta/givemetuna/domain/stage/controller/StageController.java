package com.sparta.givemetuna.domain.stage.controller;

import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.stage.dto.*;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.stage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stages")
public class StageController {

	private final StageService stageService;

	// stage 생성
	@PostMapping("/{boardId}/stages")
	public ResponseEntity<CreateStageResponseDto> createStage(@PathVariable(name = "boardId") Long boardId,
															  @RequestBody CreateStageRequestDto requestDto,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		CreateStageResponseDto response = stageService.createStage(boardId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(response);
	}

	// stage 수정
	@PatchMapping("/{stageId}")
	public ResponseEntity<UpdateStageResponseDto> updateStage(@PathVariable(name = "stageId") Long stageId,
							@RequestBody UpdateStageRequestDto requestDto,
							@AuthenticationPrincipal UserDetailsImpl userDetails) {

		UpdateStageResponseDto response = stageService.updateStage(stageId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(response);
	}

	// 페이징 및 정렬
	@GetMapping
	public void getStages() {
	}

	// stage 삭제
	@DeleteMapping("/{stageId}")
	public ResponseEntity<DeleteStageResponseDto> deleteStage(@PathVariable(name = "stageId") Long stageId,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		stageService.deleteStage(stageId, userDetails.getUser());
		return ResponseEntity.ok().body(new DeleteStageResponseDto("Stage deleted successfully"));
	}

	// stage 단건 조회
	@GetMapping("/{stageId}")
	public ResponseEntity<SingleStageResponseDto> getStage(@PathVariable(name = "stageId") Long stageId) {
		Stage stage = stageService.getStageById(stageId);
		SingleStageResponseDto response = SingleStageResponseDto.of(stage);
		return ResponseEntity.ok().body(response);
	}
}
